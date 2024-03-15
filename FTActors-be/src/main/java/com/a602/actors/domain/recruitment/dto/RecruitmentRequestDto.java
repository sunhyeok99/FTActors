package com.a602.actors.domain.recruitment.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class RecruitmentRequestDto {
    private Long id;
    private String title;
    private String content;
    private Long postMemberId;
    private String category;
    private String image;
    private String startDate;
    private String endDate;
    private Long memberId;

    @Builder
    public RecruitmentRequestDto(
            Long id,
            String title,
            String content,
            Long postMemberId,
            String category,
            String image,
            String startDate,
            String endDate,
            Long memberId
    ) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.postMemberId = postMemberId;
        this.category = category;
        this.image = image;
        this.startDate = startDate;
        this.endDate = endDate;
        this.memberId = memberId;
    }
    @Builder
    public RecruitmentRequestDto(
            String title,
            String content,
            Long postMemberId,
            String category,
            String image,
            String startDate,
            String endDate,
            Long memberId
    ) {
        this.title = title;
        this.content = content;
        this.postMemberId = postMemberId;
        this.category = category;
        this.image = image;
        this.startDate = startDate;
        this.endDate = endDate;
        this.memberId = memberId;
    }
}
