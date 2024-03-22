package com.a602.actors.global.auth.filter;

import com.a602.actors.global.auth.service.redis.RedisService;
import com.a602.actors.global.auth.util.ClientId;
import com.a602.actors.global.auth.util.CookieUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
@Slf4j
@RequiredArgsConstructor
public class KakaoAuthenticationTokenFilter extends OncePerRequestFilter {
    private final RedisService redisService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.debug("---------------- 필터 타기 -----------------");
        log.debug("[KakaoAuthenticationTokenFilter] - 필터 시작");

        // 쿠키 유무 확인
        Cookie tokenCookie = CookieUtil.resolveToken(request);

        String token = null;
        // 쿠키에서 access token 가져오기
        if(tokenCookie != null) {
            token = tokenCookie.getValue();
            log.debug("[KakaoAuthenticationTokenFilter] - 쿠키의 AccessToken: {}", token);
        }

        //토큰이 있는 경우 유저 정보를 레디스에서 가져와 SecurityContextHolder에 정보를 등록 그리고 넘긴다!
        if(token != null){
            // 토큰 유효성 검사 - 레디스에 있는지 확인, 만약 accessToken이 레디스에 없으면 로그인 만료
            log.debug("[KakaoAuthenticationTokenFilter] - 토큰 유효성 테스트 시작");

            // 토큰으로 레디스에서 정보 가져오기
            Map<String, Object> userData = redisService.getMapData(token);

            //토큰은 있지만 accessToken이 만료된 상태라면 refresh 토큰으로 재발급
            if(!userData.isEmpty()) {
                LocalDateTime start = (LocalDateTime) userData.get("createAt");
                LocalDateTime end = LocalDateTime.now();

                // 시간 확인
                Duration diff = Duration.between(start, end);
                long diffMin = diff.toMinutes();
                log.debug("[KakaoAuthenticationTokenFilter] - diff time : {}", diffMin);

                //1440 - 하루(카카오 기준 발급시 1일 유지) - 3시간
                if(diffMin >= 180){
                    // accessToken 재발급
                    String newToken = getNewToken((String)userData.get("refreshToken"));
                    log.debug("[KakaoAuthenticationTokenFilter] - access token 재발급 : {}", newToken);

                    LocalDateTime now = LocalDateTime.now();
                    String pastToken = token;

                    Map<String, Object> newRedisData = new HashMap<>();
                    // 인증 객체
                    newRedisData.put("authenticationToken", userData.get("authenticationToken"));
                    // 유저 정보
                    newRedisData.put("oauth2User", userData.get("oauth2User"));
                    // refresh token
                    newRedisData.put("refreshToken", userData.get("refreshToken"));
                    newRedisData.put("createAt", now);

                    // 토큰 갱신
                    redisService.saveMapData(newToken, newRedisData, diffMin);
                    int time = (60*60*3) + (60*60*9);
                    // 쿠키 갱신
                    response.setHeader("Set-Cookie",
                            "token=" + newToken + "; " +
                                    "Path=/;" +
//                                    "HttpOnly; " +
                                    "Max-Age=" +
                                    time
                    );
//                    // 기존 인증 객체 불러오기
                    SecurityContextHolder.getContext()
                            .setAuthentication((Authentication)userData.get("authenticationToken"));

                    // 이전 토큰, 쿠키 제거
                    redisService.deleteMapData(pastToken);
                    tokenCookie.setPath("/");
                    tokenCookie.setMaxAge(0);
                    response.addCookie(tokenCookie);

                }
                // 토큰이 만료되지 않은 상태
                else {
                    // 기존 인증 객체 불러오기
                    SecurityContextHolder.getContext()
                            .setAuthentication((Authentication)userData.get("authenticationToken"));
                }
                log.debug("[KakaoAuthenticationTokenFilter] - 토큰 유효성 테스트 결과 : true");
            }
            // 쿠키에 토큰은 있지만 레디스에 없는 경우 쿠키 날리기
            else {
                tokenCookie.setMaxAge(0);   // 쿠키 만료 시키기
                log.info("[KakaoAuthenticationTokenFilter] - 토큰 유효성 테스트 결과 : false");
            }
        }
        filterChain.doFilter(request, response);
    }

    // 토큰 재발급
    private String getNewToken(String refreshToken) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        MediaType mediaType = new MediaType("application", "x-www-form-urlencoded", StandardCharsets.UTF_8);
        headers.setContentType(mediaType);

        // 요청 매개변수 설정
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();

        map.add("grant_type", "refresh_token");
        map.add("client_id", ClientId.clientId);
        map.add("refresh_token", refreshToken);
        map.add("client_secret", ClientId.clientSecret);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        // 토큰 요청
        ResponseEntity<String> response = restTemplate.postForEntity("https://kauth.kakao.com/oauth/token", request, String.class);

        // 응답 처리
        String responseBody = response.getBody();

        // JSON 파싱
        JSONObject jsonObject = new JSONObject(responseBody);

        // 액세스 토큰 추출
        String accessToken = jsonObject.getString("access_token");
        log.info("new Token : {}", accessToken);

        return accessToken; // 추출한 토큰을 반환
    }

}
