package com.a602.actors.domain.member.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class MemberDTO {
    private String memberId;
    private String password;
    private String email;
    private String phone;

    @Builder
    public MemberDTO(String memberId, String password, String email, String phone){
        this.memberId = memberId;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }
}
