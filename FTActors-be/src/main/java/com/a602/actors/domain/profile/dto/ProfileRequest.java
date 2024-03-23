package com.a602.actors.domain.profile.dto;

//프로필 생성 시 필요한 dto

import lombok.*;

@Getter
//@Builder
@NoArgsConstructor
@ToString
public class ProfileRequest {

    //    private Member member; //로그인된 회원, 회원 정보 받아오기 (추후 보안을 위해 dto로 변경하든가)

    // jwt 만들어지고 나면 없앨 거임
    private String stageName;
    private Long memberId;
    // ------jwt 만들어지고 나면 없앨 거임
    private Character type; // A 또는 P
    private String content; // 자기소개
    private String portfolioLink;
    private Character privateProfile; // T 또는 F

    @Builder
    public ProfileRequest(String stageName,
                          Long memberId,
                          Character type,
                          String content,
                          String portfolioLink)

    { //생성할 때
        this.type = type;
        this.content = content;
        this.portfolioLink = portfolioLink;
        this.privateProfile = 'F';

        //추후 삭제
        this.stageName = stageName;
        this.memberId = memberId;
        //추후 삭제
    }

    @Builder
    public ProfileRequest(Character type,
                          String content,
                          String portfolioLink,
                          Character privateProfile)
    { //수정할 때
        this.type = type;
        this.content = content;
        this.portfolioLink = portfolioLink;
        this.privateProfile = privateProfile;
    }
}
