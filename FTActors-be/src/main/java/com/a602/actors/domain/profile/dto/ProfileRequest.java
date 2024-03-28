package com.a602.actors.domain.profile.dto;

//프로필 생성 시 필요한 dto

import lombok.*;

@Getter
//@Builder
@NoArgsConstructor
@ToString
public class ProfileRequest {

    //    private Member member; //로그인된 회원, 회원 정보 받아오기 (추후 보안을 위해 dto로 변경하든가)

    private Character type; // A 또는 P
    private String content; // 자기소개
//    private String portfolioLink; //목록에서 받을 사안이 아님
    private Character privateProfile; // T 또는 F

    @Builder
    public ProfileRequest(Character type,
                          String content)

    { //생성할 때
        this.type = type;
        this.content = content;
        this.privateProfile = 'F';
    }

    @Builder
    public ProfileRequest(String content,
                          Character privateProfile)
    { //수정할 때
//        this.type = type; 생각해보니 A, P는 못 바꾸는 게 맞다, 필요하면 새로 만드는 게 맞을 거 같다
        this.content = content;
        this.privateProfile = privateProfile;
    }
}
