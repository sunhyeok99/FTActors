package com.a602.actors.domain.recruitment.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class RecruitmentResponseDto {
    private Long id;
    private String title;
    private String content;
    private Long postMemberId;
    private String category;
    private String image;
    private String startDate;
    private String endDate;
    private int wishlist;

    @Builder
    public RecruitmentResponseDto(
            Long id,
            String title,
            String content,
            Long postMemberId,
            String category,
            String image,
            String startDate,
            String endDate,
            int wishlist
    ) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.postMemberId = postMemberId;
        this.category = category;
        this.image = image;
        this.startDate = startDate;
        this.endDate = endDate;
        this.wishlist = wishlist;
    }

}
