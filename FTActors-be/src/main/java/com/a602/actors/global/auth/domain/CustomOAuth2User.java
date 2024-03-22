package com.a602.actors.global.auth.domain;

import com.a602.actors.domain.member.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

/**
 * DB에서 불러온 UserEntity, OAuth2User 정보 커스텀 마이징
 * OAuth2User 객체가 Redis에 저장
 * Controller에서는 이 객체를 통해 사용자 정보를 받아와 DTO를 생성해 사용
 */
public class CustomOAuth2User implements OAuth2User, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final OAuth2User oauth2User;
    private Member Member;

    public CustomOAuth2User(OAuth2User oauth2User, Member member) {
        this.oauth2User = oauth2User;
        this.Member = member;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return oauth2User.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return oauth2User.getAuthorities();
    }

    @Override
    public String getName() {
        return oauth2User.getName();
    }

    public Member getMember(){
        return Member;
    }

    public void setMember(Member kakaoMember){
        this.Member = kakaoMember;
    }
}
