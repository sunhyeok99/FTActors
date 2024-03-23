package com.a602.actors.domain.montage.entity;


import com.a602.actors.domain.member.Member;
import com.a602.actors.domain.montage.dto.MontageReportDto;
import com.a602.actors.global.common.entity.BaseEntity;
import com.querydsl.core.annotations.QueryProjection;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="report")
@Getter
public class Report extends BaseEntity {

    @ManyToOne
    @JoinColumn(name="reporter_id")
    private final Member reporter;

    @ManyToOne
    @JoinColumn(name="reportee_id")
    private final Member reportee;

    private final String reason;
    @Column(name="image_link")
    private final String link;


    @Builder
    public Report(Member reporter, Member reportee, String reason, String link) {
        this.reporter = reporter;
        this.reportee = reportee;
        this.reason = reason;
        this.link = link;
    }

}
