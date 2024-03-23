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

    private String title;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id", name = "member_id")
    private Member member;

    private String category;

    private String image;

    @Column(name="saved_name")
    private String savedName;

    private String startDate;

    private String endDate;

    @Column(name = "private_recruitment")
    private String privateRecruitment; //추가 (비공개여부) - sy


    @Builder
    public Recruitment(
            String title,
            String content,
            Member member,
            String category,
            String image,
            String startDate,
            String endDate,
            String savedName,
            String privateRecruitment
    ) {
        this.title = title;
        this.content = content;
        this.member = member;
        this.category = category;
        this.image = image;
        this.savedName = savedName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.privateRecruitment = privateRecruitment;
    }

    // 변경할 인자는제목과 내용 변경 가능하게
    public void updateRecruitment(String title, String content, String category, String image, String savedName, String startDate, String endDate) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.image = image;
        this.savedName = savedName;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    // 마감일자 변경
    public void updateEndDate(String endDate){
        this.endDate = endDate;
    }
}
