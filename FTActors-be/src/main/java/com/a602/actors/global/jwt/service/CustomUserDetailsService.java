package com.a602.actors.global.jwt.service;

import java.util.ArrayList;

import com.a602.actors.domain.member.Member;
import com.a602.actors.global.exception.CustomException;
import com.a602.actors.global.jwt.repository.JWTMemberRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {
    private final JWTMemberRepository jwtMemberRepository;

    /**
     *
     * @param username 사용자 로그인 Id
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member findMember = jwtMemberRepository.findByUserId(username)
                .orElseThrow(() -> new CustomException("사용자가 존재하지 않습니다."));
        UserDetails userDetails = new User(
                findMember.getUserId(),
                findMember.getPassword(),
                new ArrayList<>()
        );

        log.info("userDetails : {}", userDetails.toString());

        return userDetails;
    }
}