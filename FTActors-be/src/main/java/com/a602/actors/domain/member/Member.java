package com.a602.actors.domain.member;

import com.a602.actors.global.common.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Entity
@Getter
@DynamicUpdate
@NoArgsConstructor
@Setter
@AllArgsConstructor
@Table(name="member")
public class Member extends BaseEntity implements Serializable {
    @Column(name = "login_id")
    private String userId;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "kakao_id")
    private String kakaoId;

    @Column(name="oauth_type")
    private String oauthType;

    @Column(name="phone")
    private String phone;

    @Column(name="birth")
    private String birth;

    @Column(name="gender")
    private String gender;

    @Column(name="profile_image")
    private String profileImage;

    @Column(name="stage_name")
    private String stageName;

    @Builder
    public Member(String email, String phone, String birth, String gender, String profileImage, String stageName){
        this.email = email;
        this.phone = phone;
        this.birth = birth;
        this.gender = gender;
        this.profileImage = profileImage;
        this.stageName = stageName;
    }
}
