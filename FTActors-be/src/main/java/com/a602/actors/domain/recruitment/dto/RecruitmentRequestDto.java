package com.a602.actors.domain.recruitment.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@Getter
@Setter
public class RecruitmentRequestDto {
    private Long id;
    private String title;
    private String content;
    private Long postMemberId;
    private String category;
    private MultipartFile image;
    private String startDate;
    private String endDate;
    private Long memberId;
    private MultipartFile file;

    @Builder
    public RecruitmentRequestDto(
            Long id,
            String title,
            String content,
            Long postMemberId,
            String category,
            MultipartFile image,
            String startDate,
            String endDate,
            Long memberId,
            MultipartFile file
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
        this.file = file;
    }
    @Builder
    public RecruitmentRequestDto(
            String title,
            String content,
            Long postMemberId,
            String category,
            MultipartFile image,
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

