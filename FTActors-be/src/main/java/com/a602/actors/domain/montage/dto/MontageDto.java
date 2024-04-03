package com.a602.actors.domain.montage.dto;

import com.a602.actors.domain.montage.entity.Montage;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

public class MontageDto {

    @Builder
    @Getter
    @Setter
    public static class Montages{
        private final Long montageId;
        private final String title;
        private final String link;
        private final Long likeCount;
        private final LocalDateTime created_at;
        private final LocalDateTime updated_at;

        @QueryProjection
        public Montages(Long montageId, String title, String link, Long likeCount, LocalDateTime created_at, LocalDateTime updated_at){
            this.montageId = montageId;
            this.title = title;
            this.link = link;
            this.likeCount = likeCount;
            this.created_at = created_at;
            this.updated_at = updated_at;
        }


        public static MontageDto.Montages toDto(Montage montage){
            return Montages.builder()
                    .montageId(montage.getId())
                    .title(montage.getTitle())
                    .link(montage.getLink())
                    .created_at(montage.getCreatedAt())
                    .updated_at(montage.getUpdatedAt())
                    .build();
        }
    }
}
