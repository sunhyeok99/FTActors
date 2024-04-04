package com.a602.actors.domain.member;

import com.a602.actors.global.common.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Column(name = "member_id")
    private String memberId;

    private String password;

    private String email;

    private String phone;

    @Builder
    public Member(String memberId, String password, String email, String phone){
        this.memberId = memberId;
        this.password = password;
        this.email = email;
        this.phone = phone;

    }
}
