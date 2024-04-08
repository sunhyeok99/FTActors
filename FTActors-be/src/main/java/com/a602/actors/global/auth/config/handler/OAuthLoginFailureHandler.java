package com.a602.actors.global.auth.config.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * OAuthLoginFailureHandler 구현
 * 로그인 과정 실패시 로그로 에러 메세지 출력
 * 실제로 로그인이 실패 시 카카오에서 로그인 실패 처리를 하기 때문에 보여지지 않는다.
 */

@Slf4j
@Component
public class OAuthLoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        log.error("LOGIN FAILED : {}", exception.getMessage());
        super.onAuthenticationFailure(request, response, exception);
    }
}