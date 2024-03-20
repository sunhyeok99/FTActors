package com.a602.actors.domain.montage.entity;


import com.a602.actors.domain.member.Member;
import com.a602.actors.global.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Entity
@Table(name = "montage")
@Getter
@Builder
public class Montage extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "member_id", referencedColumnName = "id")
    private Member member;
    private String title;
    private String link;

    public Montage() {

    }


    public Montage(Member member, String title, String link){
        this.member = member;
        this.title = title;
        this.link = link;
    }

//    public Montage toEntity(MontageDto.Montages montage){
//        return Montage.builder()
//                .member(member)
//                .title(montage.getTitle())
//                .link(montage.getLink())
//                .build();
//
//    }

}
