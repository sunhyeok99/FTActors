package com.a602.actors.domain.montage.entity;

import com.a602.actors.domain.member.Member;
import com.a602.actors.global.common.entity.BaseEntity;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.querydsl.core.annotations.QueryProjection;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name="comment")
@Getter
public class Comment extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "member_id", referencedColumnName = "id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "montage_id", referencedColumnName = "id")
    private Montage montage;
    private String content;
    private Long referenceId;

    public Comment() {

    }
    @QueryProjection
    public Comment(Member member, Montage montage, String content, Long referenceId){
        this.member = member;
        this.montage = montage;
        this.content = content;
        this.referenceId = referenceId;
    }

}
