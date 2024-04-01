package com.a602.actors.global.jwt.controller;

import com.a602.actors.global.common.dto.ApiResponse;
import com.a602.actors.global.jwt.dto.JwtDto;
import com.a602.actors.global.jwt.service.JWTMemberServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
public class JWTController {
    private final JWTMemberServiceImpl jwtMemberService;

//    @PostMapping("/signup")
//    public ApiResponse<String> regist(@RequestBody JwtDto.Simple jwtDto, MultipartFile multipartFile) throws IOException {
//        log.info("Success register login_id : {}", jwtDto.getLoginId());
//        return new ApiResponse<>(HttpStatus.OK.value(), "sign up success", jwtMemberService.signup(jwtDto));
//    }

    @PostMapping("/signup")
    public ApiResponse<String> regist(@RequestPart(value = "dto") JwtDto.Simple jwtDto,
                                      @RequestPart(value = "profileImage") MultipartFile profileImage) throws IOException {
        log.info("Success register login_id : {}", jwtDto.getLoginId());
        return new ApiResponse<>(HttpStatus.OK.value(), "sign up success", jwtMemberService.signup(jwtDto, profileImage));
    }

    @PostMapping("/signin")
    public ApiResponse<JwtDto.AuthResponse> login(@RequestBody JwtDto.AuthRequest memberDto) {
        log.info("로그인 시도 : {}", memberDto.getLoginId());
        JwtDto.AuthResponse member = jwtMemberService.signin(memberDto);
        log.info("로그인 결과 : {}", member.getLoginId());

        if (member.getLoginId() != null) {
            return new ApiResponse<>(HttpStatus.OK.value(), "sign in success", member);
        } else {
            return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), "sign in failed", member);
        }
    }
    @PostMapping("/loginmember")
    public ApiResponse<JwtDto.getPkId> getIdByLoginId(@RequestBody String userId){
        JwtDto.getPkId dto= jwtMemberService.getIdByLoginId(userId);
        return new ApiResponse<>(HttpStatus.OK.value(), "get Id by login id success", dto);
    }

    @PostMapping("/check-id")
    public ApiResponse<JwtDto.checkIdResult> checkId(@RequestBody JwtDto.checkId memberId) {
        return new ApiResponse<>(HttpStatus.OK.value(), "id can be used", jwtMemberService.isDuplicatedId(memberId.getId()));
    }

//    @PostMapping("/check-nickname")
//    public ApiResponse<JwtDto.checkIdResult> checkNickname(@RequestBody JwtDto.checkNickname memberNickname) {
//        return ApiResponse.success(SuccessCode.GET_SUCCESS, memberService.isDuplicatedNickname(memberNickname.getNickname()));
//    }

    @PostMapping("/refresh")
    public ApiResponse<JwtDto.AuthResponse> reIssue(@RequestBody JwtDto.AuthResponse tokenRequest) {
        log.info("Refresh Token 재발행 시작 !!!!!!!!!!");
        JwtDto.AuthResponse authResponse = jwtMemberService.reIssue(tokenRequest);
        return new ApiResponse<>(HttpStatus.OK.value(), "The refresh token has been regenerated", authResponse);
    }

    @DeleteMapping("/logout")
    public ApiResponse<String> logout() {
        log.info("로그아웃 시작 !!!!!!!!");
        jwtMemberService.logout();
        return new ApiResponse<>(HttpStatus.OK.value(), "로그아웃 성공");
    }

//    @DeleteMapping("/quit")
//    public ApiResponse<String> quitMember() {
//        log.info("회원탈퇴 시작 !!!!!!!!!");
//        memberService.deleteMember();
//        return ApiResponse.success(SuccessCode.DELETE_SUCCESS, "회원탈퇴 성공");
//    }
@PutMapping("/update")
public ApiResponse<String> updateUser(@RequestBody JwtDto.UpdateRequest updateRequest) {
    log.info("사용자 정보 수정 시도: {}", updateRequest.getLoginId());
    jwtMemberService.updateUser(updateRequest);
    return new ApiResponse<>(HttpStatus.OK.value(), "User information updated successfully", "");
}
}
