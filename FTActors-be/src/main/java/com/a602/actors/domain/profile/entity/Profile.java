package com.a602.actors.domain.profile.entity;

import com.a602.actors.domain.member.Member;
import com.a602.actors.global.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity(name = "profile")
@Getter
@NoArgsConstructor
@DynamicUpdate // 수정되는 항만 update되게
//@AllArgsConstructor
//@RequiredArgsConstructor
@ToString
@EntityListeners(AuditingEntityListener.class) //시간 찍기
public class Profile extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "member_id", referencedColumnName = "id")
    private Member member; //회원 번호 (member테이블에서 가져옴)

    private String content; //자기소개

    private Character type; //'P', 'A' (기본으로 A 설정)

    private String portfolio; //포트폴리오 링크

    @Column(name = "private_post")
    private Character privatePost; //'T', 'F' (기본으로 F 설정)

    private String image;

    private String imageName;
    @Builder
    public Profile (Member member, String content,
                    Character type,
                    String portfolio,
                    Character privatePost, String image, String imageName) {
        this.member = member;
        this.content = content;
        this.type = type;
        this.portfolio = portfolio;
        this.privatePost = privatePost;
        this.image = image;
        this.imageName = imageName;
    }

    public void updateProfile(Character type , String content, String image, String imageName, String portfolio, Character privatePost){
        this.type = type;
        this.content = content;
        this.image = image;
        this.imageName = imageName;
        this.portfolio = portfolio;
        this.privatePost = privatePost;

    }

}
