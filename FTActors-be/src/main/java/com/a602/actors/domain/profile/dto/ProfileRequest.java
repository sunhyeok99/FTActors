package com.a602.actors.domain.profile.dto;

//프로필 생성 시 필요한 dto

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
//@Builder
@NoArgsConstructor
@ToString
public class ProfileRequest {

    //    private Member member; //로그인된 회원, 회원 정보 받아오기 (추후 보안을 위해 dto로 변경하든가)

    private Long memberId;
    private Character type; // A 또는 P
    private String content; // 자기소개
    private String portfolioLink;
    private MultipartFile image;
    private Character privateProfile; // T 또는 F

    @Builder
    public ProfileRequest(Long memberId,
                          Character type,
                          String content, MultipartFile image,
                          String portfolioLink, Character privateProfile)

    { //생성할 때
        this.memberId = memberId;
        this.type = type;
        this.content = content;
        this.image = image;
        this.portfolioLink = portfolioLink;
        this.privateProfile = 'F';
    }

    @Builder
    public ProfileRequest(Long memberId,
                          String content,
                          Character privateProfile,
                          String portfolioLink)
    { //수정할 때
        this.memberId = memberId;
//        this.type = type; 생각해보니 A, P는 못 바꾸는 게 맞다, 필요하면 새로 만드는 게 맞을 거 같다
        this.content = content;
        this.privateProfile = privateProfile;
        this.portfolioLink = portfolioLink;
    }
}
