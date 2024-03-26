package com.a602.actors.domain.member.service;

import org.springframework.stereotype.Service;

import com.a602.actors.domain.member.Member;
import com.a602.actors.domain.member.dto.MemberDTO;
import com.a602.actors.domain.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository;

    public void register(MemberDTO memberDTO){
        Member newMember = Member.builder()
                .userId(memberDTO.getLoginId())
                .password(memberDTO.getPassword())
                .email(memberDTO.getEmail())
                .phone(memberDTO.getPhone())
                .build();
        memberRepository.save(newMember);
    }

    public boolean login(MemberDTO memberDto){
        Member member = memberRepository.findByuserId(memberDto.getLoginId());
        return member != null && member.getPassword().equals(memberDto.getPassword());
    }
}
