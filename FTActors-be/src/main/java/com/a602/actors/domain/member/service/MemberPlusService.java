package com.a602.actors.domain.member.service;

import com.a602.actors.domain.member.dto.MemberPlusDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface MemberPlusService {
    void register(MemberPlusDTO memberPlusDTO, MultipartFile profileImage, String kakaoId) throws IOException;
//    boolean login(MemberPlusDTO memberPlusDTO, );

}
