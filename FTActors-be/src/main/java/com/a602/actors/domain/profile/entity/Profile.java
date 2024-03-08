package com.a602.actors.domain.profile.entity;

import com.a602.actors.global.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Entity(name = "profile")
@Data
@NoArgsConstructor
//@RequiredArgsConstructor
@DynamicUpdate // 수정되는 항만 update되게
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class) //시간 찍기
public class Profile extends BaseEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    @Column(name = "member_id")
    private Long memberId;

    private String content;

    private Character type; //'P', 'A'

//    @CreatedDate
//    @Column(name = "created_at")
//    private Date createdAt;
//
//    @CreatedDate
//    @Column(name = "updated_at")
//    private Date updatedAt;

//    @PrePersist //createdAt은 처음 값에서 변하지 않게 하기 (-> 테스트 해보기!)
//    protected void onCreat() {
//        if(this.createdAt == null) {
//            this.createdAt = new Date();
//        }
//        this.updatedAt = this.createdAt;
//    }
}
