package com.a602.actors.domain.montage.dto;

import com.a602.actors.domain.montage.entity.Montage;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

public class MontageDto {

    @RequiredArgsConstructor
    @Builder
    @Getter
    @Setter
    public static class MontageInfo{
        private final String title;
        private final String link;
        private final Integer likeCount;

        public static MontageDto.MontageInfo toDto(Montage montage){
            return MontageInfo.builder()
                    .title(montage.getTitle())
                    .link(montage.getLink())
                    .likeCount(montage.getLikeCount())
                    .build();
        }
    }

}
