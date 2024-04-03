package com.a602.actors.domain.member.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.a602.actors.domain.member.dto.MemberDtoForChat;
import com.a602.actors.domain.member.dto.MemberPlusDTO;

public interface MemberPlusService {
    void update(MemberPlusDTO memberPlusDTO, MultipartFile profileImage, String kakaoId) throws IOException;
    List<MemberDtoForChat> findAllMembersByStageName(String stageName);
}
