package com.a602.actors.global.jwt.config;

import com.a602.actors.global.jwt.JwtTokenProvider;
import com.a602.actors.global.jwt.filter.JWTCustomFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class JWTSecurityConfig {
    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public SecurityFilterChain jwtFilterChain(HttpSecurity http) throws Exception {
//        http
//                .cors(CorsConfigurer::disable) // WebMvcConfig에 있는 cors 설정 사용
//                .cors(Customizer.withDefaults())
//                // .csrf(CsrfConfigurer::disable)
//                .csrf(AbstractHttpConfigurer::disable) //csrf 비활성
//                .httpBasic(AbstractHttpConfigurer::disable) //HTTP 기본인증 비활성
//                // 시큐리티가 세션을 만들지도 사용하지도 않음.
//                .sessionManagement((sessionManagement) ->
//                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                )
//                .addFilterAfter(new JWTCustomFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
//
//        ;
//        return http.build();
//    }
}
