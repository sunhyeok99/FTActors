package com.a602.actors.global.auth.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class KakaoDto {
    @Data
    @Builder
    public static class Token {
        private String access_token;
        private String refresh_token;
    }

    @Data
    public static class ReissueToken{
        private String access_token;
        private String token_type;
        private String refresh_token;
        private Long refresh_token_expires_in;
    }
    @Data
    @Builder
    public static class MemberInfo {
        private Long kakaoId;
        private String kakaoAccessToken;
        private String kakaoRefreshToken;
    }
}
