package com.a602.actors.domain.member.controller;

import com.a602.actors.domain.member.dto.MemberDTO;
import com.a602.actors.domain.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/register")
    public String registerMember(@RequestBody MemberDTO memberDTO){
        memberService.register(memberDTO);
        return "User registered successfully";
    }

    @PostMapping("/login")
    public String loginMember(@RequestBody MemberDTO memberDTO, HttpSession session){
        if(memberService.login(memberDTO)){
            session.setAttribute("memberName", memberDTO.getMemberId());
            return "Login Success";
        }
        else{
            return "Login Failed";
        }
    }

    @PostMapping("logout")
    public String logoutMember(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "Logout successful";
    }
}
