package com.a602.actors.domain.profile.controller;

import com.a602.actors.domain.member.Member;
import com.a602.actors.domain.profile.dto.ProfileDto;
import com.a602.actors.domain.profile.dto.ProfileRequest;
import com.a602.actors.domain.profile.dto.ProfileSearchResponse;
import com.a602.actors.domain.profile.entity.ProfileDocument;
import com.a602.actors.domain.profile.service.ProfileService;
import com.a602.actors.global.auth.service.member.MemberService;
import com.a602.actors.global.auth.util.CookieUtil;
import com.a602.actors.global.common.dto.ApiResponse;
import com.a602.actors.global.jwt.util.JWTUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/profile")
@Slf4j
public class ProfileController {
    private final ProfileService profileService;
    private final JWTUtil jwtUtil;
    private final CookieUtil cookieUtil;
    private final MemberService memberService;

    //어케 쓸 수 있겠찌
    public Long findMemberLoginedMemberId(HttpServletRequest request) {
        Long loginedId = jwtUtil.getLoginMemberId();
        log.info("loginedId: " + loginedId);
        if (loginedId == null) {
            Cookie cookie = cookieUtil.resolveToken(request);
            Map<String, String> attributes = cookie.getAttributes();
            String loginedKakaoId = attributes.get("kakaoId");
            Member loginedMemeber = memberService.getUserByKakaoId(loginedKakaoId);
            loginedId = loginedMemeber.getId();
        }

        return loginedId;
    }

    @GetMapping("/list") //1.완성 //To do: 멤버 받아서 본인 확인 후, 비공개여부 T인 것도 리스트에 같이 받아오기, 시큐리티 영향x 처리 필요
    public ApiResponse<List<ProfileSearchResponse>> getAllProfileList(@RequestParam(name = "sort") int sorting)
    {
        log.info("배우,감독 프로필 전체 목록 - 컨트롤러");

        List<ProfileSearchResponse> results = profileService.searchAllProfile(sorting);
        return new ApiResponse<>(HttpStatus.OK.value(), "프로필 전체 목록을 불러왔습니다.", results);
    }

    @GetMapping("/detail") //db만 사용 -> jwt에서 멤버 가져오는 걸로 바꾸기, 시큐리티 영향x 처리 필요
    public ApiResponse<ProfileDto> getDetailProfile(@RequestParam(name = "profile_id") Long profileId,
                                                        HttpSession session)
    {
        log.info("프로필 상세 페이지");

        //To do: session에서 유저 아이디 뽑아오는 거 -> jwt로 바꾸기

        return new ApiResponse<>(HttpStatus.OK.value(), "해당 프로필을 불러왔습니다.", profileService.getProfile(profileId, session));
    }

    @GetMapping("/searchcontent") //->삭제까지 구현하고 다시, 시큐리티 영향x 처리 필요
    public ApiResponse<?> searchByContent(
            @RequestParam(value = "keywords") String keywords) {

        if (keywords==null || keywords.isEmpty() || keywords.isBlank() || keywords.length()==0) {
            return new ApiResponse<>(HttpStatus.OK.value(), "프로필 검색 결과입니다.", profileService.searchAllProfile(1));
        }
        String[] keywordArr = keywords.split(" "); //공백 기준으로 키워드 여러 개 인식

        // 배열이 아닌 리스트로 검색어를 보내는 이유
        List<ProfileSearchResponse> profileSearchResponses = profileService.searchProfileByContent(Arrays.asList(keywordArr));

        // To do: 키워드 다중 검색
        // To do: 형태소 분석... (다 나눠서 찾기 > 가중치 > 오타 잡기 > 자동완성)
        return new ApiResponse<>(HttpStatus.OK.value(), "프로필 검색 결과입니다.", profileSearchResponses);
    }

    @GetMapping("/searchstagename") //->삭제까지 구현하고 다시, 시큐리티 영향x 처리 필요
        public ApiResponse<?> searchByStageName( @RequestParam(value = "stage_name") String findName) {
        // 배열이 아닌 리스트로 검색어를 보내는 이유
        List<ProfileSearchResponse> profileSearchResponses = profileService.searchProfileByStageName(findName);

        // To do: 키워드 다중 검색
        // To do: 형태소 분석... (다 나눠서 찾기 > 가중치 > 오타 잡기 > 자동완성)
        return new ApiResponse<>(HttpStatus.OK.value(), "프로필 검색 결과입니다.", profileSearchResponses);
    }

    @GetMapping("/searchname") //->삭제까지 구현하고 다시, 시큐리티 영향x 처리 필요
    public ApiResponse<?> searchByName( @RequestParam(value = "name") String findName) {
        // 배열이 아닌 리스트로 검색어를 보내는 이유
        List<ProfileSearchResponse> profileSearchResponses = profileService.searchProfileByName(findName);

        // To do: 키워드 다중 검색
        // To do: 형태소 분석... (다 나눠서 찾기 > 가중치 > 오타 잡기 > 자동완성)
        return new ApiResponse<>(HttpStatus.OK.value(), "프로필 검색 결과입니다.", profileSearchResponses);
    }

    /////////////////////-----------------------위에는 read, 아래는 cud

    //프로필 생성
    @PostMapping("/myprofile") // -> 추후에 stageName대신 jwt
    public ApiResponse<String> createProfile(@RequestBody ProfileRequest profileRequest) { //파라미터 추후에 변경
        log.info("프로필 만들기~! ");

        String result = profileService.createProfile(profileRequest);

        return new ApiResponse<>(HttpStatus.OK.value(), "프로필을 성공적으로 생성했습니다.", result);
    }

    //프로필 삭제
    @DeleteMapping("/myprofile") // -> jwt에서 로그인 정보 뽑아오기
    public ApiResponse<String> removeProfile(@RequestParam(name = "profile_id") Long profileId) {
        log.info("프로필 삭제하기!");

        String result = "";
//        profileService222.deleteProfile(profileId);
        profileService.deleteProfile(profileId);

        return new ApiResponse<>(HttpStatus.OK.value(), "프로필을 성공적으로 삭제했습니다.", result);
    }

    //프로필 수정
    @PutMapping("/myprofile") // -> jwt에서 로그인 정보 뽑아오기
    public ApiResponse<String> modifyProfile(@RequestParam(name = "profile_id") Long profileId,
                                              @RequestBody ProfileRequest profileRequest)
    {
        log.info("프로필 수정하기~! ");
        
        return new ApiResponse<>(HttpStatus.OK.value(), "프로필을 성공적으로 수정했습니다.", profileService.updateProfile(profileId, profileRequest));
    }

}
