package com.a602.actors.global.jwt.filter;

import com.a602.actors.global.exception.TokenException;
import com.a602.actors.global.jwt.JwtTokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class JWTCustomFilter extends OncePerRequestFilter {
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private final JwtTokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        System.out.println("URI :::::::::: " + request.getRequestURI());

        // 1. 토큰이 필요하지 않은 API URL에 대해서 배열로 구성한다.
        List<String> list = Arrays.asList(
                "/signup",		// 회원가입 페이지
                "/signin",		// 로그인 페이지
                "/check-id" 	// 아이디 중복 검사
        );

        // 2. 토큰이 필요하지 않은 API URL의 경우 -> 로직 처리없이 다음 필터로 이동한다.
        if (list.contains(request.getRequestURI()) || request.getRequestURI().contains("recruitment") || request.getRequestURI().contains("follow")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = resolveToken(request);
        if (token == null) {
            filterChain.doFilter(request, response);
            //throw new CustomException("토큰이 없어요.");
        }
//        log.info("JwtFilter ::::::::: resolvedToken = {}", token.toString());

        boolean isValidate = false;
        boolean isRefresh = false;
        try {
            isValidate = tokenProvider.validateToken(token);
        } catch (TokenException e) {
            if(! request.getRequestURI().equals("/api/auth/refresh")){
                throw new TokenException("토큰의 유효 기간이 만료되었습니다.");
            } else {
                isRefresh = true;
            }
        }

        if((StringUtils.hasText(token) && isValidate) || isRefresh) {
            log.info("JwtFilter ::::::::: 유효한 토큰입니다.");
            //토큰 값에서 Authentication 값으로 가공해서 반환 후 저장
            Authentication authentication;
            // if(isRefresh) authentication = tokenProvider.getPlainAuthentication(token);
            // else authentication = tokenProvider.getAuthentication(token);
            authentication = tokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            log.info("JwtFilter ::::::::: Security Context에 '{}' 인증 정보를 저장했습니다", authentication.getName());
            log.info("JwtFilter ::::::::: Security Context에 저장되어 있는 인증 정보 입니다. '{}'", SecurityContextHolder.getContext().getAuthentication().getName());
        } else {
            log.info("JwtFilter ::::::::: 유효한 JWT 토큰이 없습니다.");
        }

        //다음 필터로 넘기기
        filterChain.doFilter(request, response);
    }

    /**
     * HttpServletRequest에서 `Authorization` 헤더를 받음.
     * 헤더에서 'Bearer'로 시작하는 토큰이 있으면 'Bearer' 부분 제거하고 토큰 값 반환 아니면 널 값 반환
     * @param request
     * @return
     */
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) return bearerToken.substring(7);

        return null;
    }
}
