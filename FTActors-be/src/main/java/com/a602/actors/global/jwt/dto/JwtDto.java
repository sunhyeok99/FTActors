package com.a602.actors.global.jwt.dto;

import lombok.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.time.LocalDateTime;

public class JwtDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AuthRequest {
        @Setter
        private String loginId;
        private String password;

        public UsernamePasswordAuthenticationToken toAuthentication() {
            return new UsernamePasswordAuthenticationToken(loginId, password);
        }
    }
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class JwtToken{
        private String loginId;
        private String password;
        private String jwt_accessToken;
        private String jwt_refreshToken;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AuthResponse {
        private String loginId;
        private String grantType;
        private String accessToken;
        private String refreshToken;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Simple {
        private String loginId;
        private String password;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class checkIdResult {
        private Boolean isDuplicate;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class checkId {
        private String id;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class getPkId{
        private Long id;
        private String loginId;
        private String name;
        private String email;
        private String phone;
        private String birth;
        private String gender;
        private String profileImage;
        private String stageName;
        private LocalDateTime createdAt;
    }
}
