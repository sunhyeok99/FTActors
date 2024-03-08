package com.a602.actors.domain.recruitment.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class RecruitmentResponseDto {
    private Long id;
    private String title;
    private String content;
    private Long memberId;
    private String category;
    private String image;
    private String startDate;
    private String endDate;

    @Builder
    public RecruitmentResponseDto(
            Long id,
            String title,
            String content,
            Long memberId,
            String category,
            String image,
            String startDate,
            String endDate
    ) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.memberId = memberId;
        this.category = category;
        this.image = image;
        this.startDate = startDate;
        this.endDate = endDate;
    }

}
