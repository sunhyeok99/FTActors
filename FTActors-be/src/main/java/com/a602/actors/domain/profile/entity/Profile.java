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
@AllArgsConstructor
//@RequiredArgsConstructor
@Builder
@ToString
@EntityListeners(AuditingEntityListener.class) //시간 찍기
//public class Profile extends BaseEntity {
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "member_id")
    private Long memberId;

//    @OneToOne
//    @JoinColumn(name = "member_id")
//    private Member member;

    private String content;

    private Character type; //'P', 'A'

    @CreatedDate
    @Column(name = "created_at")
    private Date createdAt;

    @CreatedDate
    @Column(name = "updated_at")
    private Date updatedAt;

    @PrePersist //createdAt은 처음 값에서 변하지 않게 하기 (-> 테스트 해보기!)
    protected void onCreat() {
        if(this.createdAt == null) {
            this.createdAt = new Date();
        }
        this.updatedAt = this.createdAt;
    }

//    @Builder
//    public Profile ( Long memberId, String content, Character type, Date createdAt, Date updatedAt) {
//        Assert.notNull(content, "자기소개 작성 요망!");
//        Assert.notNull(type, "배우인가요 관계자인가요?");
//
//        this.memberId = memberId;
//        this.content = content;
//        this.type = type;
//        this.createdAt = createdAt;
//        this.updatedAt = updatedAt;
//    }
}
