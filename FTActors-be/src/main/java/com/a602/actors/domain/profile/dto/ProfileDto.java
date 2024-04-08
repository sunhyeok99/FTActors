package com.a602.actors.domain.profile.dto;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProfileDto {
//    private Long id; //프로필 고유 번호
//    private Long memberId; //회원 아이디
//    private String content; //자기소개
//    private Character type; //배우 프로필? 관계자 프로필?
//    private String portfolio; //포트폴리오 링크
//    private Character privatePost; //공개여부
private Long id;
    private Long memberId; //회원 아이디
    private String name;
    private String stageName; //stage_name
    private String content;
    private Character type;
    private String gender; //성별 추가 (불변)
    private String portfolio;
    private String birth; //생년월일 추가 (불변, 나이 기반 검색)
    private Character privatePost; //private_post
    private String imageLink;
    private String createdTime; //created_time
    private String updatedTime; //updated_time


}
