package com.a602.actors.domain.member.service;

import com.a602.actors.domain.member.Member;
import com.a602.actors.domain.member.dto.MemberPlusDTO;
import com.a602.actors.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberPlusRepository;

    public void register(MemberPlusDTO memberDTO){
        Member newMember = Member.builder()
                .memberId(memberDTO.getMemberId())
                .password(memberDTO.getPassword())
                .email(memberDTO.getEmail())
                .phone(memberDTO.getPhone())
                .build();
        memberPlusRepository.save(newMember);
    }

    public boolean login(MemberPlusDTO memberPlusDto){
        Member member = memberPlusRepository.findByMemberId(memberPlusDto.getMemberId());
        return member != null && member.getPassword().equals(memberPlusDto.getPassword());
    }
}
