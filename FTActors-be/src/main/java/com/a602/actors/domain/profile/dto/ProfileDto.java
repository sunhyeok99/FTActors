package com.a602.actors.domain.profile.dto;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProfileDto {
    private Long id; //프로필 고유 번호
    private Long memberId; //회원 아이디
    private String content; //자기소개
    private Character type; //배우 프로필? 관계자 프로필?
    private String portfolio; //포트폴리오 링크
//    private ArrayList<String> additionalImages; //추가 이미지
    private Character privatePost; //공개여부
}
