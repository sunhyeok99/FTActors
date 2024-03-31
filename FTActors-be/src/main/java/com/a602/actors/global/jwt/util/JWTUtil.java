package com.a602.actors.global.jwt.util;

import com.a602.actors.domain.member.Member;
import com.a602.actors.global.exception.CustomException;
import com.a602.actors.global.jwt.dto.JwtDto;
import com.a602.actors.global.jwt.mapper.MemberMapper;
import com.a602.actors.global.jwt.repository.JWTMemberRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class JWTUtil {
    private final JWTMemberRepository jwtMemberRepository;
    private final MemberMapper memberMapper;

    public Long getLoginMemberId() {

        // 현재 로그인한 유저의 id(pk)를 반환
//        log.info("getLoginMemberId ::::::::::: memberId '{}'", SecurityContextHolder.getContext().getAuthentication().getName());
        Member member = jwtMemberRepository.findByLoginId(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(() -> new CustomException("일치하는 사용자가 없습니다."));
        log.info("MemberUtil getLoginMemberId ::::::::: id '{}'", member.getId());
        return member.getId();
    }

}