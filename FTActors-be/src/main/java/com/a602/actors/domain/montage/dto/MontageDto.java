package com.a602.actors.domain.montage.dto;

import com.a602.actors.domain.montage.entity.Montage;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

public class MontageDto {

    @Builder
    @Getter
    @Setter
    @RequiredArgsConstructor
    public static class Montages{
        private final String title;
        private final String link;
        private final Integer likeCount;

        public static MontageDto.Montages toDto(Montage montage){
            return Montages.builder()
                    .title(montage.getTitle())
                    .link(montage.getLink())
                    .build();
        }
    }
}
