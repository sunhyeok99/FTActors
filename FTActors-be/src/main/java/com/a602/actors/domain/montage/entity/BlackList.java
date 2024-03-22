package com.a602.actors.domain.montage.entity;


import com.a602.actors.domain.member.Member;
import com.a602.actors.global.common.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name="blacklist")
@Getter
public class BlackList extends BaseEntity {
    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;

    private Character warning;
}
