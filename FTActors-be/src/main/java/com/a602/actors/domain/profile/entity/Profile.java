package com.a602.actors.domain.profile.entity;

import com.a602.actors.domain.member.Member;
import com.a602.actors.global.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.util.Assert;

import java.util.Date;

@Entity(name = "profile")
@Getter
@NoArgsConstructor
@DynamicUpdate // 수정되는 항만 update되게
//@AllArgsConstructor
//@RequiredArgsConstructor
@ToString
@EntityListeners(AuditingEntityListener.class) //시간 찍기
public class Profile extends BaseEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member; //회원 번호 (member테이블에서 가져옴)

    private String content; //자기소개

    private Character type; //'P', 'A' (기본으로 A 설정)

    private String portfolio; //포트폴리오 링크

    @Column(name = "private_post")
    private Character privatePost; //'T', 'F' (기본으로 F 설정)

//    @CreatedDate
//    @Column(name = "created_at")
//    private Date createdAt;
//
//    @CreatedDate
//    @Column(name = "updated_at")
//    private Date updatedAt;
//
//    @PrePersist //createdAt은 처음 값에서 변하지 않게 하기 (-> 테스트 해보기!)
//    protected void onCreat() {
//        if(this.createdAt == null) {
//            this.createdAt = new Date();
//        }
//        this.updatedAt = this.createdAt;
//    }

    @Builder
    public Profile (Member member, String content, Character type, String portfolio, Character privatePost) {
        Assert.notNull(content, "자기소개 작성 요망!");
        Assert.notNull(type, "배우인가요 관계자인가요?");

        this.member = member;
        this.content = content;
        this.type = type;
        this.portfolio = portfolio;
        this.privatePost = privatePost;
    }
}
