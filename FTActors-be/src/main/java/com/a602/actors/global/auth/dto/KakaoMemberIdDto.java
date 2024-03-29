package com.a602.actors.global.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KakaoMemberIdDto {
    private Long id;
    private String kakaoId;
    private String name;
    private String email;
    private String oauthType;
    private String phone;
    private String birth;
    private String gender;
    private String profileImage;
    private String stageName;
    private LocalDateTime createdAt;

}
