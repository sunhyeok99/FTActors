package com.a602.actors.domain.member.service;

import com.a602.actors.domain.member.Member;
import com.a602.actors.domain.member.dto.MemberDTO;
import com.a602.actors.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository;

    public void register(MemberDTO memberDTO){
        Member newMember = Member.builder()
                .memberId(memberDTO.getMemberId())
                .password(memberDTO.getPassword())
                .email(memberDTO.getEmail())
                .phone(memberDTO.getPhone())
                .build();
        memberRepository.save(newMember);
    }

    public boolean login(MemberDTO memberDto){
        Member member = memberRepository.findByMemberId(memberDto.getMemberId());
        return member != null && member.getPassword().equals(memberDto.getPassword());
    }
}
