package com.a602.actors.domain.montage.dto;

import com.a602.actors.domain.montage.entity.Montage;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

public class MontageDto {

    @Builder
    @Getter
    @Setter
    @RequiredArgsConstructor
    public static class Montages{
        private final String title;
        private final String link;
        private final LocalDateTime created_at;
        private final LocalDateTime updated_at;

        public static MontageDto.Montages toDto(Montage montage){
            return Montages.builder()
                    .title(montage.getTitle())
                    .link(montage.getLink())
                    .created_at(montage.getCreatedAt())
                    .updated_at(montage.getUpdatedAt())
                    .build();
        }
    }
}
