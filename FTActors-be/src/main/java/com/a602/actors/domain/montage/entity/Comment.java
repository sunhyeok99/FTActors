package com.a602.actors.domain.montage.entity;

import com.a602.actors.domain.member.Member;
import com.a602.actors.domain.montage.dto.MontageCommentDto;
import com.a602.actors.domain.montage.dto.MontageDto;
import com.a602.actors.global.common.entity.BaseEntity;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.querydsl.core.annotations.QueryProjection;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name="comment")
@Getter
@Builder
public class Comment extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "member_id", referencedColumnName = "id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "montage_id", referencedColumnName = "id")
    private Montage montage;

    private String content;

    private Long referenceId;

    @ColumnDefault("false")
    @Column(name = "is_deleted", columnDefinition = "TINYINT")
    private boolean isDeleted;

    public Comment() {

    }
    @QueryProjection
    public Comment(Member member, Montage montage, String content, Long referenceId, boolean isDeleted){
        this.member = member;
        this.montage = montage;
        this.content = content;
        this.referenceId = referenceId;
        this.isDeleted = isDeleted;
    }

    public void setContent(String content){
        this.content = content;
    }

    public void setIsDeleted(boolean state){
        this.isDeleted = state;
    }

}
