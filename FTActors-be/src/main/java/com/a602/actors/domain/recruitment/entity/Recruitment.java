package com.a602.actors.domain.recruitment.entity;

import com.a602.actors.domain.member.Member;
import com.a602.actors.global.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;


@Entity
@Getter
@DynamicUpdate // 수정되는 항만 update되게
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "recruitment")
public class Recruitment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recruitment_id")
    private Long id;

    @Column(name = "recruitment_title")
    private String title;

    @Column(name = "recruitment_content")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "recruitment_category")
    private String category;

    @Column(name = "recruitment_image")
    private String image;

    @Column(name = "recruitment_start_date")
    private String startDate;

    @Column(name = "recruitment_end_date")
    private String endDate;

    @Builder
    public Recruitment(
            String title,
            String content,
            Member member,
            String category,
            String image,
            String startDate,
            String endDate
    ) {
        this.title = title;
        this.content = content;
        this.member = member;
        this.category = category;
        this.image = image;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // 변경할 인자는제목과 내용 변경 가능하게
    public void updateRecruitment(String title, String content, String image) {
        this.title = title;
        this.content = content;
        this.image = image;
    }
    // 마감일자 변경
    public void updateEndDate(String endDate){
        this.endDate = endDate;
    }
}
