package com.a602.actors.domain.member.controller;

import com.a602.actors.domain.member.Member;
import com.a602.actors.domain.member.dto.MemberPlusDTO;
import com.a602.actors.domain.member.repository.MemberRepository;
import com.a602.actors.domain.member.service.MemberPlusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberPlusService memberPlusService;
    private final MemberRepository memberRepository;
    @PostMapping("/register")
    public ResponseEntity<String> enroll(@RequestPart(value = "dto") MemberPlusDTO memberPlusDTO,
                                         @RequestPart(value = "profileImage") MultipartFile profileImage,
                                         @RequestPart(value = "kakaoId") String kakaoId) {
        try {
            memberPlusService.register(memberPlusDTO, profileImage, kakaoId);
            return ResponseEntity.ok("User information registered successfully");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to register user information: " + e.getMessage());
        }
    }

//    public String registerMember(@RequestBody MemberPlusDTO memberPlusDTO, MultipartFile profileImage, String kakaoId) throws IOException {
//        System.out.println(kakaoId);
//        memberPlusService.register(memberPlusDTO, profileImage, kakaoId);
//        return "User information registered successfully";
//    }

//    public ResponseEntity<ResultResponse> enroll(@RequestPart(value = "dto") EnrollRequest dto,
//                                                 @RequestPart(value = "files") List<MultipartFile> files,
//                                                 @AuthenticationPrincipal AuthMember authMember) {
//        BoardResponse responseDto = boardService.create(dto, files, authMember);
//        ResultResponse result = ResultResponse.of(ResultCode.ENROLL_SUCCESS, responseDto);
//        return new ResponseEntity<>(result, HttpStatus.valueOf(result.getStatus()));
//    }
//




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
