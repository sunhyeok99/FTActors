package com.a602.actors.domain.member.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class MemberPlusDTO {
    private String memberId;
    private String password;
    private String email;
    private String phone;

    @Builder
    public MemberPlusDTO(String memberId, String password, String email, String phone){
        this.memberId = memberId;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }
}
