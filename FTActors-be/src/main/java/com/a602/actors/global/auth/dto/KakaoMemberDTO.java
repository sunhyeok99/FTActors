package com.a602.actors.global.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KakaoMemberDTO {
    private String login_id;
    private String password;
    private String name;
    private String oauthType;
}
