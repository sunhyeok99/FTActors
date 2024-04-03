package com.a602.actors.domain.member.service;

import com.a602.actors.domain.member.dto.MemberDTO;

public interface MemberService {
    void register(MemberDTO memberDTO);
    boolean login(MemberDTO memberDTO);

}
