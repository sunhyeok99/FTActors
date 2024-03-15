package com.a602.actors.domain.montage.entity;


import com.a602.actors.domain.member.Member;
import com.a602.actors.global.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;

import java.util.List;

@Entity
@Table(name="like_count")
public class LikeCount extends BaseEntity {
    @OneToOne
    private Member member;

    @ManyToOne
    @JoinColumn(name="montage_id")
    private Montage montage;

    public LikeCount() {

    }

    @Builder
    public LikeCount(Member member, Montage montage){
        this.member = member;
        this.montage = montage;
    }
}
