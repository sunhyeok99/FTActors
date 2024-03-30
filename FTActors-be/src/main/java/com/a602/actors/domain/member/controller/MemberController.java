package com.a602.actors.domain.member.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.a602.actors.domain.member.dto.MemberPlusDTO;
import com.a602.actors.domain.member.repository.MemberRepository;
import com.a602.actors.domain.member.service.MemberPlusService;
import com.a602.actors.global.common.dto.ApiResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor

public class MemberController {
    private final MemberPlusService memberPlusService;
    private final MemberRepository memberRepository;
    @PostMapping("/register")
    public ApiResponse<String> enroll(@RequestPart(value = "dto") MemberPlusDTO memberPlusDTO,
                                         @RequestPart(value = "profileImage") MultipartFile profileImage,
                                         @RequestPart(value = "kakaoId") String kakaoId) {
        try {
            memberPlusService.update(memberPlusDTO, profileImage, kakaoId);
            return new ApiResponse<>(HttpStatus.OK.value(), "User information registered successfully", memberPlusService.toString());
        } catch (IOException e) {
            return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), "User information registered failed", memberPlusService.toString());
        }
    }

    @GetMapping("/api/findall/{stageName}")
    public ApiResponse<List<MemberPlusDTO>> findAllMembersByStageName(@PathVariable String stageName){
        List<MemberPlusDTO> allMembersByStageName = memberPlusService.findAllMembersByStageName(stageName);
        return new ApiResponse<>(HttpStatus.OK.value(), "조회 성공 !!", allMembersByStageName);
    }

//    @PostMapping("/login")
//    public String loginMember(@RequestBody MemberPlusDTO memberDTO, HttpSession session){
//        if(memberService.login(memberDTO)){
//            session.setAttribute("memberName", memberDTO.getMemberId());
//            return "Login Success";
//        }
//        else{
//            return "Login Failed";
//        }
//    }
//
//    @PostMapping("logout")
//    public String logoutMember(HttpServletRequest request, HttpServletResponse response) {
//        HttpSession session = request.getSession(false);
//        if (session != null) {
//            session.invalidate();
//        }
//        return "Logout successful";
//    }
}
