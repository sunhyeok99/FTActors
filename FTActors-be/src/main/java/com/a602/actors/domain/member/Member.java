package com.a602.actors.domain.member;

import java.io.Serializable;

import org.hibernate.annotations.DynamicUpdate;

import com.a602.actors.global.common.entity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@DynamicUpdate
@NoArgsConstructor
@Setter
@AllArgsConstructor
@Builder
@Table(name="member")
public class Member extends BaseEntity implements Serializable {
    @Column(name = "login_id")
    private String loginId;

    @Column(name = "password")
    private String password;

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

    @Column(name="saved_name")
    private String savedName;

//    @Builder
//    public Member(String userId, String password, String email){
//        this.userId = userId;
//        this.password = password;
//        this.email = email;
//    }
//
//    @Builder
//    public Member(String email, String phone, String birth, String gender, String profileImage, String stageName){
//        this.email = email;
//        this.phone = phone;
//        this.birth = birth;
//        this.gender = gender;
//        this.profileImage = profileImage;
//        this.stageName = stageName;
//    }

}
