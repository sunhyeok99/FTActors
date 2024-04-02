package com.a602.actors.domain.apply.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@Getter
@Setter
public class ApplyDto {
    private Long id;
    private Long recruitmentId;
    private Long memberId;
    private String memberName;
    private MultipartFile videoFile;
    private String videoLink;
    private String content;
    private String createdAt;
    @Builder
    public ApplyDto(
            Long id,
            Long recruitmentId,
            String memberName,
            String videoLink,
            String content,
            String createdAt
    ) {
        this.id = id;
        this.recruitmentId = recruitmentId;
        this.memberName = memberName;
        this.videoLink = videoLink;
        this.content = content;
        this.createdAt = createdAt;
    }

    @Builder
    public ApplyDto(
            Long recruitmentId,
            Long memberId,
            MultipartFile videoFile,
            String content
    ) {
        this.recruitmentId = recruitmentId;
        this.memberId = memberId;
        this.videoFile = videoFile;
        this.content = content;
    }



}
