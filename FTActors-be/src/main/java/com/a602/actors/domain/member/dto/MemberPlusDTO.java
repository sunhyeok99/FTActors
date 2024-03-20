package com.a602.actors.domain.member.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class MemberPlusDTO {
    private String email;
    private String phone;
    private String birth;
    private String gender;
    private String profileImage;
    private String stageName;

    @Builder
    public MemberPlusDTO(String email, String phone, String birth, String gender, String profileImage, String stageName){
        this.email = email;
        this.phone = phone;
        this.birth = birth;
        this.gender = gender;
        this.profileImage = profileImage;
        this.stageName = stageName;
    }
}
