package com.a602.actors.global.auth.config.handler;

import com.a602.actors.global.auth.domain.CustomOAuth2User;
import com.a602.actors.global.auth.service.member.MemberService;
import com.a602.actors.global.auth.service.redis.RedisService;
import com.a602.actors.global.auth.util.CookieUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
public class CustomLogoutHandler implements LogoutHandler {
    // 레디스 서비스
    private final RedisService redisService;
    private final MemberService memberService;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        // 쿠키에 저장된 access token 가져오기
        Cookie tokenCookie = CookieUtil.resolveToken(request);
        String accessToken = null;
        if(tokenCookie != null)
            accessToken = tokenCookie.getValue();
        log.debug("[CustomLogoutHandler] - Access Token : {}", accessToken);

        // 로그아웃이 2번에 걸쳐서 진행됨
        if (accessToken != null) {
            log.info("[CustomLogoutHandler] - 1차 로그아웃");

            // db에 있는 deviceToken 지우기
            // 로그아웃 필터가 유저 인증 필터 보다 앞에 있기 때문에 회원 정보는 레디스에 얻어와야 한다.
            Map<String, Object> userData = redisService.getMapData(accessToken);
            CustomOAuth2User customOAuth2User = (CustomOAuth2User) userData.get("oauth2User");
            log.info("유저 아이디: {}", customOAuth2User.getMember().getUserId());
            // 레디스에 저장된 회원 데이터 지우기
            redisService.deleteMapData(accessToken);

            // 쿠키에 저장된 access token 지우기
            Cookie[] cookies = request.getCookies();
            if(cookies != null) {
                for(int i = cookies.length-1; i>=0; i--){
                    Cookie curr = cookies[i];
                    curr.setPath("/");
                    curr.setMaxAge(0);
                    response.addCookie(curr);
                }
            }
        }
        else{
            log.debug("[CustomLogoutHandler] - 2차 로그아웃");
        }
    }
}
