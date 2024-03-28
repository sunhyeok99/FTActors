package com.a602.actors.domain.recruitment.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class RecruitmentListResponseDto {
    private Long id;
    private String title;
    private String image;
    private String endDate;
    private int wishList;
    private String privateRecruitment;

    @Builder
    public RecruitmentListResponseDto(
            Long id,
            String title,
            String image,
            String endDate,
            int wishlist,
            String privateRecruitment
    ) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.endDate = endDate;
        this.wishList = wishlist;
        this.privateRecruitment = privateRecruitment;
    }

}
