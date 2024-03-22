package com.a602.actors.domain.member.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class MemberDTO {
    private String loginId;
    private String password;
    private String email;
    private String phone;

    @Builder
    public MemberDTO(String loginId, String password, String email, String phone){
        this.loginId = loginId;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }
}
