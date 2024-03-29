package com.a602.actors.global.auth.controller;

import com.a602.actors.global.auth.domain.CustomOAuth2User;
import com.a602.actors.global.auth.dto.KakaoMemberIdDto;
import com.a602.actors.global.auth.service.member.MemberService;
import com.a602.actors.global.auth.service.redis.RedisService;
import com.a602.actors.global.auth.util.CookieUtil;
import com.a602.actors.global.common.dto.ApiResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.util.Map;

@Controller
@Slf4j
@RequiredArgsConstructor
public class AuthenticationController {
    private final RedisService redisService;
    private final MemberService memberService;

    private Cookie tokenCookie;

    private final int time = (60*60*24*14) + (60*60*9);
    /**
     * 로그인
     * @return
     */
    @GetMapping("/auth/login")
    public String login() {
        return "login";
    }
    /**
     * 로그인 상태 유지 14일
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/auth/login-status")
    public ResponseEntity<?> loginStatus(HttpServletRequest request, HttpServletResponse response) {
        HttpStatus status;
        tokenCookie = CookieUtil.resolveToken(request);
        String accessToken = tokenCookie.getValue();

        if(memberService.modifyUserLoginStatus(accessToken)) {
            status = HttpStatus.ACCEPTED;
            tokenCookie.setMaxAge(time);
            log.info("쿠키 남은 시간: {}", tokenCookie.getMaxAge());
            tokenCookie.setPath("/");
            response.addCookie(tokenCookie);
        }
        else
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        return new ResponseEntity<>(status);
    }

    /**
     * 메인 페이지
     * @return
     */

    @GetMapping("/main")
    public String mainPage() {
        return "mainPage";
    }
    /**
     * 에러  페이지
     * @param model
     * @return
     */
    public String mainPage(Model model, @AuthenticationPrincipal CustomOAuth2User oauth2User, HttpServletRequest request) {
        // 레디스 세션에서 사용
        log.info("user : {}", oauth2User.getMember().getUserId());

        tokenCookie = CookieUtil.resolveToken(request);
        Map<String, Object> userData = redisService.getMapData(tokenCookie.getValue());

        model.addAttribute("userId", oauth2User.getMember().getUserId());
//        model.addAttribute("password", oauth2User.getMember().getPassword());
        model.addAttribute("kakaoId", oauth2User.getMember().getKakaoId());
        model.addAttribute("oauthType", oauth2User.getMember().getOauthType());
        return "userInfo";
    }

    @PostMapping("/kakaomember")
    public ApiResponse<KakaoMemberIdDto> getIdBykakaoId(@RequestBody String kakaoId){
        KakaoMemberIdDto dto= memberService.getIdByKakaoId(kakaoId);
        return new ApiResponse<>(HttpStatus.OK.value(), "get Id by kakao id success", dto);
    }

}
