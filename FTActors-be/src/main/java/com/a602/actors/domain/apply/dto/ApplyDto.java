package com.a602.actors.domain.apply.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ApplyDto {
   private Long recruitmentId;
   private Long memberId;
   private String videoLink;
   private String content;

    @Builder
    public ApplyDto(
            Long recruitmentId,
            Long memberId,
            String videoLink,
            String content
    ) {
        this.recruitmentId = recruitmentId;
        this.memberId = memberId;
        this.videoLink = videoLink;
        this.content = content;
    }

}
