package com.a602.actors.domain.profile.dto;

import com.a602.actors.domain.member.Member;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProfileListDto {
    private Long id; //프로필 고유 번호
    private String memberId; //회원 아이디
    private String content; //자기소개
    private String portfolio; //포트폴리오 링크
    private Character privatePost; //공개여부
}
