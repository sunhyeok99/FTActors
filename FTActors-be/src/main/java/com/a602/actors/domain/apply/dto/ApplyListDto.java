package com.a602.actors.domain.apply.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ApplyListDto {
    private Long id;
    private String recruitmentTitle;
    private String memberName;
    private String videoLink;
    private String videoName;
    private String createdAt;
    @Builder
    public ApplyListDto(
            Long id,
            String recruitmentTitle,
            String memberName,
            String videoLink,
            String videoName,
            String createdAt
    ) {
        this.id = id;
        this.recruitmentTitle = recruitmentTitle;
        this.memberName = memberName;
        this.videoLink = videoLink;
        this.videoName = videoName;
        this.createdAt = createdAt;
    }

}
