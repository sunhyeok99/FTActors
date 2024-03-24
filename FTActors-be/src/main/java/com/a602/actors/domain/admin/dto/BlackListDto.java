package com.a602.actors.domain.admin.dto;

import com.a602.actors.domain.montage.entity.BlackList;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


public class BlackListDto {

    @Getter
    @Setter
    public static class BlackListSet{
        private final String email;
        private final String name;

        @QueryProjection
        @Builder
        public BlackListSet(String email, String name){
            this.email = email;
            this.name = name;
        }

        public static BlackListSet toDto(BlackList list){
            return BlackListSet
                    .builder()
                    .email(list.getMember().getEmail())
                    .name(list.getMember().getStageName())
                    .build();
        }


    }
}
