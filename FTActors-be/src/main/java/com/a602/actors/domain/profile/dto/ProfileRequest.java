package com.a602.actors.domain.profile.dto;

//프로필 생성 시 필요한 dto

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@ToString
public class ProfileRequest {

    //    private Member member; //로그인된 회원, 회원 정보 받아오기 (추후 보안을 위해 dto로 변경하든가)

    private Character condition; // A 또는 P
    private String selfIntroduction; // 자기소개
    private String portfolioLink;
    private Character privateProfile; // T 또는 F

    @Builder
    public ProfileRequest(Character condition,
                          String selfIntroduction,
                          String portfolioLink)

    {
        this.condition = condition;
        this.selfIntroduction = selfIntroduction;
        this.portfolioLink = portfolioLink;
        this.privateProfile = 'F';
    }

    @Builder
    public ProfileRequest(Character condition,
                          String selfIntroduction,
                          String portfolioLink,
                          Character privateProfile)
    {
        this.condition = condition;
        this.selfIntroduction = selfIntroduction;
        this.portfolioLink = portfolioLink;
        this.privateProfile = privateProfile;
    }
}
