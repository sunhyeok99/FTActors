package com.a602.actors.domain.profile.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
public class ProfileSearchResponse implements Serializable {
    // 게시물 목록 양식에 들어갈 데이터만 제공
    // ??? - articleId는 MariaDB에 찾으러 갈 때 필요
    private Long id;
    private String stageName;
    private String content;
    private Character type;
    private String portfolio;
    private Character privatePost;
    private LocalDateTime updatedTime;

    private Double distance; //가중치인가요?
}