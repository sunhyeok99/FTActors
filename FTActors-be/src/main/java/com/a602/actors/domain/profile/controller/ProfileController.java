package com.a602.actors.domain.profile.controller;

import com.a602.actors.domain.profile.dto.ProfileDto;
import com.a602.actors.domain.profile.dto.ProfileRequest;
import com.a602.actors.domain.profile.dto.ProfileSearchResponse;
import com.a602.actors.domain.profile.entity.ProfileDocument;
import com.a602.actors.domain.profile.service.ProfileService;
import com.a602.actors.global.common.dto.ApiResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/profile")
@Slf4j
public class ProfileController {
    private final ProfileService profileService;

    @GetMapping("/list") //1.완성 //To do: 멤버 받아서 본인 확인 후, 비공개여부 T인 것도 리스트에 같이 받아오기
    public ApiResponse<List<ProfileSearchResponse>> getAllProfileList(@RequestParam(name = "sort") int sorting)
    {
        log.info("배우,감독 프로필 전체 목록 - 컨트롤러");

        List<ProfileSearchResponse> results = profileService.searchAllProfile(sorting);
        return new ApiResponse<>(HttpStatus.OK.value(), "프로필 전체 목록을 불러왔습니다.", results);
    }

    @GetMapping("/detail") //그냥 db에서 가져와야 하는 게 맞음 -> jwt에서 멤버 가져오는 걸로 바꾸기
    public ApiResponse<ProfileDto> getDetailProfile(@RequestParam(name = "profile_id") Long profileId,
                                                        HttpSession session)
    {
        log.info("프로필 상세 페이지");

        //To do: session에서 유저 아이디 뽑아오는 거 -> jwt로 바꾸기

        return new ApiResponse<>(HttpStatus.OK.value(), "해당 프로필을 불러왔습니다.", profileService.getProfile(profileId, session));
    }

    //검색하는 메서드 예시... list를 그대로 써도..?
    @GetMapping("/search")
    public ApiResponse<?> searchPost(
            @RequestParam(value = "keywords") String keywords) {
        String[] keywordArr = keywords.split(" "); //공백 기준으로 키워드 여러 개 인식

        // 배열이 아닌 리스트로 검색어를 보내는 이유
        // 가변 인수 처리의 편의성 때문
        List<ProfileDocument> profileDocuments = profileService.searchProfileDocuments(Arrays.asList(keywordArr));

        // To do: 키워드 다중 검색
        // To do: 형태소 분석... (다 나눠서 찾기 > 가중치 > 오타 잡기 > 자동완성)
        return new ApiResponse<>(HttpStatus.OK.value(), "프로필 검색 결과입니다.", profileDocuments);
    }

//    @GetMapping("/search")
//    public ApiResponse<List<ProfileSearchResponse>> search(@Validated @ModelAttribute ProfileSearchRequest profileSearchRequest)
//    {
//        log.info("도현이의 코드를 따서 만든 키워드 검색 - 엘라스틱서치");
//        List<ProfileSearchResponse> results = profileService.search(profileSearchRequest);
//        System.out.println(results);
//        return new ApiResponse<>(HttpStatus.OK.value(), "프로필 키워드 검색 결과입니다.", results);
//    }


    /////////////////////-----------------------위에는 read, 아래는 cud

    //프로필 생성
    @PostMapping("/myprofile") //개어려워
    public ApiResponse<String> createProfile(@RequestBody ProfileRequest profileRequest, HttpSession session) { //파라미터 추후에 변경
        log.info("프로필 만들기~! ");

        String result = profileService.createProfile(profileRequest, session);
//        Profile newProfile = new Profile();
//        elasticsearchOperations.save(ProfileDocument.from(newProfile));

        return new ApiResponse<>(HttpStatus.OK.value(), "프로필을 성공적으로 생성했습니다.", profileService.createProfile(profileRequest, session));
    }

    //프로필 삭제
    @DeleteMapping("/myprofile")
    public ApiResponse<String> removeProfile(@RequestParam(name = "profile_id") Long profileId) {
        log.info("프로필 삭제하기!");

        String result = "";
//        profileService222.deleteProfile(profileId);
        profileService.deleteProfile(profileId);

        return new ApiResponse<>(HttpStatus.OK.value(), "프로필을 성공적으로 삭제했습니다.", result);
    }

    //프로필 수정
    @PutMapping("/myprofile") //개어려워
    public ApiResponse<String> modifyProfile(@RequestParam(name = "profile_id") Long profileId, HttpSession session,
                                              @RequestBody ProfileRequest profileRequest)
    {
        log.info("프로필 수정하기~! ");
        return new ApiResponse<>(HttpStatus.OK.value(), "프로필을 성공적으로 수정했습니다.", profileService.updateProfile(profileId, profileRequest, session));
    }


}
