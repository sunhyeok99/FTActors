package com.a602.actors.domain.admin.dto;

import com.a602.actors.domain.montage.entity.BlackList;
import lombok.Builder;

public class BlackListDto {
    public static class BlackListSet{
        private final String email;
        private final String name;

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
