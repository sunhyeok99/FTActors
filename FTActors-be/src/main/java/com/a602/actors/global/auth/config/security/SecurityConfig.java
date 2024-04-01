package com.a602.actors.global.auth.config.security;

import static jakarta.servlet.DispatcherType.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.annotation.web.configurers.RememberMeConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.a602.actors.global.auth.config.handler.CustomAuthenticationEntryPoint;
import com.a602.actors.global.auth.config.handler.CustomLogoutHandler;
import com.a602.actors.global.auth.config.handler.OAuthLoginFailureHandler;
import com.a602.actors.global.auth.config.handler.OAuthLoginSuccessHandler;
import com.a602.actors.global.auth.filter.KakaoAuthenticationTokenFilter;
import com.a602.actors.global.auth.service.member.MemberService;
import com.a602.actors.global.auth.service.redis.RedisService;
import com.a602.actors.global.jwt.JwtTokenProvider;
import com.a602.actors.global.jwt.filter.JWTCustomFilter;

import lombok.RequiredArgsConstructor;

/**
 * Spring Security 설정
 * Logout시 Handler 설정(Spring Chain Filter 순서상 2번 이므로 앞쪽으로 설정함)
 * 에러 핸들러
 * CORS 설정, CSRF 설정 생략
 * OAuth 인증을 사용하여 일반 로그인 기능 끔
 * 로그인 상태를 토큰으로 관리하기 때문에 무상태 유지(세션으로 관리x)
 * 사용자 정의 필터를 정의하여 토큰이 있는 경우 로그인 인증 절차 넘김
 * URL에 대한 인증처리
 * Oauth2.0 로그인 설정
 * 로그인 성공시 처리 로직
 * 로그인 실패시 처리 로직
 */

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final OAuthLoginSuccessHandler oAuthLoginSuccessHandler;
    private final OAuthLoginFailureHandler oAuthLoginFailureHandler;
    private final MemberService memberService;
    private final RedisService redisService;

    //    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    @Value("${CLIENT_ID}")
    String clientId;

    // 리다이렉트 URL
    @Value("${auth-redirect-url}")
    String redirectUrl;

//    @Value("${project.front-url}")
//    String frontUrl;

    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public CustomLogoutHandler customLogoutHandler() {
        return new CustomLogoutHandler(redisService, memberService);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .logout(logout -> logout
                        .logoutUrl("/auth/logout")
                        .logoutSuccessUrl("https://kauth.kakao.com/oauth/logout?client_id=" + clientId
                                + "&logout_redirect_uri=" + redirectUrl)
                        .deleteCookies("JSESSIONID", "token") // 쿠키 삭제
                        .invalidateHttpSession(true) // 세션 무효화
                        .addLogoutHandler(customLogoutHandler())
                )
                .exceptionHandling(error -> error
                        .authenticationEntryPoint(new CustomAuthenticationEntryPoint()))
//                .cors(corsConfigurer -> corsConfigurer.configurationSource(corsConfigurationSource()))
                .cors(Customizer.withDefaults())
                .csrf(CsrfConfigurer::disable)
                .httpBasic(HttpBasicConfigurer::disable)
                .formLogin(FormLoginConfigurer::disable)
                .rememberMe(RememberMeConfigurer::disable)
                .sessionManagement(
                        (session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
//                .addFilterAfter(new KakaoAuthenticationTokenFilter(redisService), UsernamePasswordAuthenticationFilter.class)
//                .addFilterAfter(new JWTCustomFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
//                .authorizeHttpRequests(request -> request
//                        .dispatcherTypeMatchers(FORWARD, ERROR).permitAll()
//                        .requestMatchers("/auth/**", "/main", "/error", "/static/**", "/signin",
//                                "/firebase/**", "/css/**","/js/**", "/firebase-messaging-sw.js",
//                                "/barter/**", "/post/**", "/register", "/signup", "/ws-stomp"
//                                ,"/oauth2/authorization/kakao", "/chat/**", "/notify/**"
//                            // Todo : "/chat/**", "/notify/**" 삭제
//                        ).permitAll()
//                        .anyRequest().authenticated()
//                )
                // oauth2.0 로그인 설정
                .oauth2Login(oAuth -> oAuth
                        .loginPage("/login")
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(memberService)
                        )
                        .successHandler(oAuthLoginSuccessHandler)
                        .failureHandler(oAuthLoginFailureHandler)
                );

        return http.build();
    }

//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        return request -> {
//            CorsConfiguration config = new CorsConfiguration();
//            config.setAllowedHeaders(Collections.singletonList("*"));
//            config.setAllowedMethods(Collections.singletonList("*"));
////            config.setAllowedOriginPatterns(Collections.singletonList(frontUrl));
//            config.setAllowCredentials(true);
//            return config;
//        };
//    }
}
