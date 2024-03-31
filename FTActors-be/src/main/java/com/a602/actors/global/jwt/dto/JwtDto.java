package com.a602.actors.global.jwt.dto;

import lombok.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

public class JwtDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AuthRequest {
        @Setter
        private String memberId;
        private String password;

        public UsernamePasswordAuthenticationToken toAuthentication() {
            return new UsernamePasswordAuthenticationToken(memberId, password);
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
        private String memberId;
        private String grantType;
        private String accessToken;
        private String refreshToken;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    public static class Simple {
        private String loginId;
        private String password;
        private String name;
        private String email;
        private String phone;
        private String birth;
        private String gender;
        private MultipartFile profileImage;
        private String stageName;
        private String savedName;
        @Builder
        public Simple(String loginId, String password, String name, String email, String phone, String birth, String gender, MultipartFile profileImage, String stageName, String savedName){
            this.loginId = loginId;
            this.password = password;
            this.name = name;
            this.email = email;
            this.phone = phone;
            this.birth = birth;
            this.gender = gender;
            this.profileImage = profileImage;
            this.stageName = stageName;
            this.savedName = savedName;
        }
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
        private String userId;
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
