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
    private String imageName;
    private String startDate;

    private String endDate;

    private String privateRecruitment;

    private String file;
    private String fileName;

    @Builder
    public Recruitment(
            String title,
            String content,
            Member member,
            String category,
            String image,
            String imageName,
            String startDate,
            String endDate,
            String file,
            String fileName
    ) {
        this.title = title;
        this.content = content;
        this.member = member;
        this.category = category;
        this.image = image;
        this.imageName = imageName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.file = file;
        this.fileName = fileName;
    }

    // 변경할 인자는제목과 내용 변경 가능하게
    public void updateRecruitment(String title, String content, String category, String image, String imageName, String startDate, String endDate, String file, String fileName) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.image = image;
        this.imageName = imageName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.file = file;
        this.fileName = fileName;
    }
    // 마감일자 변경
    public void updateEndDate(String endDate){
        this.endDate = endDate;
    }

    // 마감된 공고 처리
    public void updatePrivate(){
        this.privateRecruitment = "T";
    }
}
