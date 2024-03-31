package com.a602.actors.global.auth.config.handler;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.a602.actors.domain.member.Member;
import com.a602.actors.global.auth.domain.CustomOAuth2User;
import com.a602.actors.global.auth.service.member.MemberService;
import com.a602.actors.global.auth.service.redis.RedisService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * OAuthLoginSuccessHandler 구현
 * 회원 인증 과정이 성공적 마치면 OAuthLoginSuccessHandler에 도달
 * 현재 이 Handler에서는 CustomOAuth2User 정보와 Refresh token을 Redis에 저장
 * 로그인 상태 유지를 위해 Access token을 쿠키에 저장하고 token을 가지고 로그인 유무를 판단
 * Spring Controller에서는 Redis에 저장된 User 정보를 가지고 로직을 처리
 */
@Component
@Slf4j
public class OAuthLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Value("${auth-redirect-url}")
    String mainPage;

    // DB에서 CustomOAuth2User를 가져오기 위한 service
    private final MemberService memberService;
    // Redis에서 CustomOAuth2User를 저장하기 위한 service
    private final RedisService redisService;
    // 인증객체에서 CustomOAuth2User를 가져오기 위한 service
    private final OAuth2AuthorizedClientService authorizedClientService;

    // 생성자 주입
    @Autowired
    public OAuthLoginSuccessHandler(MemberService memberService, RedisService redisService, OAuth2AuthorizedClientService authorizedClientService) {
        this.authorizedClientService = authorizedClientService;
        this.memberService = memberService;
        this.redisService = redisService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
        Authentication authentication) throws IOException, ServletException {
        // 인증 객체
        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
        log.info("token success : " + token);
        // 발급처
        String oauthType = token.getAuthorizedClientRegistrationId();
        log.info("oauthType success : " + oauthType);
        // Access token, Refresh token을 가져오기 위한 인증 정보(인가를 하고 왔을때 토큰 정보가 저장됨)
        OAuth2AuthorizedClient authorizedClient = authorizedClientService.loadAuthorizedClient(oauthType, token.getName());
        log.info("authrizedClient : " + authorizedClient);
        // OAuth2UserRequest 생성
        OAuth2UserRequest userRequest = new OAuth2UserRequest(authorizedClient.getClientRegistration(), authorizedClient.getAccessToken());

        // OAuth2UserService를 통해 사용자 정보 로드
        CustomOAuth2User oauth2User = memberService.loadUser(userRequest);

        // accessToken, refreshToken 받아오기
        Map<String, String> tokens = getTokens(oauth2User);
        String accessToken = tokens.get("accessToken");
        log.info("accessToken : " + accessToken);
        String refreshToken = tokens.get("refreshToken");
        log.info("refreshToken : " + refreshToken);
        // 로그인 유지 상태를 체크하기 위한 시간 플래그
        LocalDateTime now = LocalDateTime.now();
        Member member = oauth2User.getMember();

        // 토큰이 없는 경우 토큰을 Redis에 저장
        Map<String, Object> redisData = new HashMap<>();

        // 유저 데이터 저장 확인
        log.debug("[OAuthLoginSuccessHandler] - UserInfo : {}, {}, {}"
            ,member.getLoginId(),
            member.getKakaoId(), member.getOauthType()
        );
        redisData.put("authenticationToken", token);
        redisData.put("oauth2User", oauth2User);
        redisData.put("refreshToken", refreshToken);
        redisData.put("createAt", now);

        // Redis에 Key access token, Value User 정보, Refresh token, 생성 시간 저장
        redisService.saveMapData(accessToken, redisData);

        log.info("redis data : {}", redisService.getMapData(accessToken));

        // 로그인 성공
        log.debug("[OAuthLoginSuccessHandler] - LOGIN SUCCESS : {} FROM {}",oauth2User.getMember().getLoginId(), oauthType);

        // 쿠키에 Access token을 저장, 3시간 유지
        int time = (60*60*3) + (60*60*9);

        // 토큰 설정
        response.addHeader("Set-Cookie",
            "token=" + accessToken + "; " +
                "Path=/;" +
                //                        "HttpOnly; " +
                "Max-Age=" +
                time
        );

        // 유저 아이디
        response.addHeader("Set-Cookie",
            "userId=" + member.getLoginId() + "; " +
                "Path=/;" +
                //                        "HttpOnly; " +
                "Max-Age=" +
                time
        );

        response.sendRedirect(mainPage);
        super.onAuthenticationSuccess(request, response, authentication);
    }

    // 토큰 정보 가져오기
    public Map<String, String> getTokens(@AuthenticationPrincipal OAuth2User oauth2User) {
        String clientRegistrationId = "kakao"; // 클라이언트 등록 ID (예: "kakao", "google" 등)
        String name = oauth2User.getName();

        // 인증 객체에 정보 불러오기
        OAuth2AuthorizedClient authorizedClient =
            authorizedClientService.loadAuthorizedClient(clientRegistrationId, name);

        // Access token
        OAuth2AccessToken accessToken = authorizedClient.getAccessToken();
        // Refresh token
        String refreshToken = authorizedClient.getRefreshToken().getTokenValue();

        // 토큰 반환
        Map<String, String> tokens = new HashMap<>();
        tokens.put("accessToken", accessToken.getTokenValue());
        tokens.put("refreshToken", refreshToken);

        log.debug("[OAuthLoginSuccessHandler] - Access Token: {}, Refresh Token:{}", accessToken.getTokenValue(), refreshToken);
        return tokens;
    }
}

