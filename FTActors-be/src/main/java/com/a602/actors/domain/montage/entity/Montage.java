package com.a602.actors.domain.montage.entity;


import com.a602.actors.domain.member.Member;
import com.a602.actors.domain.montage.dto.MontageDto;
import com.a602.actors.domain.montage.dto.MontageDto.*;
import com.a602.actors.global.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "montage")
@Builder
@Getter
public class Montage extends BaseEntity {

//    @OneToMany(mappedBy = "member")
//    private Member member;
    private String title;
    private String link;

    public Montage() {

    }

    public Montage(String title, String link){
        this.title = title;
        this.link = link;
    }

    public Montage toEntity(MontageDto.Montages montage){
        return Montage.builder()
                .title(montage.getTitle())
                .link(montage.getLink())
                .build();

    }

}
