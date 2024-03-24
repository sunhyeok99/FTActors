package com.a602.actors.domain.profile.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
@Getter
@AllArgsConstructor
@Builder
public class ProfileSearchResponse implements Serializable {
    // 게시물 목록 양식에 들어갈 데이터만 제공
    // ??? - articleId는 MariaDB에 찾으러 갈 때 필요
    private Long id;
    private Long memberId;
    private String name; //실명 추가 (거의 불변)
    private String stageName;
    private String content;
    private Character type;
//    private String portfolio;
    private String gender; //성별 추가 (불변)
    private String birth; //생년월일 추가 (불변, 나이 기반 검색)
    private Character privatePost;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;

//    private Double distance; //가중치인가요?
}