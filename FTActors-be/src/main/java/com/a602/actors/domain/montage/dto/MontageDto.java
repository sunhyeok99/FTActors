package com.a602.actors.domain.montage.dto;

import com.a602.actors.domain.montage.entity.Montage;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

public class MontageDto {

    @Builder
    @Getter
    @Setter
    public static class Montages{
        private final String title;
        private final String link;
        private final Long likeCount;

        @QueryProjection
        public Montages(String title, String link, Long likeCount){
            this.title = title;
            this.link = link;
            this.likeCount = likeCount;
        }

        public static MontageDto.Montages toDto(Montage montage){
            return Montages.builder()
                    .title(montage.getTitle())
                    .link(montage.getLink())
                    .build();
        }
    }
}
