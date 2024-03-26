package com.a602.actors.domain.apply.entity;

import com.a602.actors.domain.member.Member;
import com.a602.actors.domain.recruitment.entity.Recruitment;
import com.a602.actors.global.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "apply")
public class Apply extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id", name = "recruitment_id")
    private Recruitment recruitment;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id", name = "member_id")
    private Member member;

    private String videoLink;

    @Column(name="saved_name")
    private String savedName;

    private String content;

    @Builder
    public Apply(
            Recruitment recruitment,
            Member member,
            String videoLink,
            String content,
            String savedName
    ) {
    this.recruitment = recruitment;
    this.member = member;
    this.videoLink = videoLink;
    this.content = content;
    this.savedName = savedName;
    }
}
