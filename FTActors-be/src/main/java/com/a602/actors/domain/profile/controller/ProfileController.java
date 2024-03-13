package com.a602.actors.domain.profile.controller;

import com.a602.actors.domain.profile.dto.ProfileDto;
import com.a602.actors.domain.profile.dto.ProfileRequest;
import com.a602.actors.domain.profile.service.ProfileService;
import com.a602.actors.global.common.dto.ApiResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/profile")
@Slf4j
public class ProfileController {
    private final ProfileService profileService;

    @GetMapping("/list")
    public ApiResponse<List<ProfileDto>> getAllProfileList(@RequestParam(name = "sort") int sorting,
                                                           @RequestParam(name = "condition") Character condition,
                                                           HttpSession session)
    {
        log.info("배우,감독 프로필 전체 목록 - 컨트롤러");

//        List<Profile> profiles = profileService.getProfileList(sorting, condition); //null 가능

//        return new ResponseEntity<>(profiles, HttpStatus.OK);

        //To do: 비공개여부 True인 사람은 리스트에 나오면 안 됨 => 완료
        //To do: 로그인 한 상태라면: 공개여부가 true라도, 내 꺼는 보이게 해야 함... => 완료

        return new ApiResponse<>(HttpStatus.OK.value(), "프로필 전체 목록을 불러왔습니다.", profileService.getProfileList(sorting, condition, session));
    }

    @GetMapping("/detail")
    public ApiResponse<ProfileDto> getDetailProfile(@RequestParam(name = "profile_id") Long profileId,
                                                        HttpSession session)
    {
        log.info("프로필 상세 페이지");

        //To do: 비공개여부 True인 사람은 볼 수 없어야 함
        //To do: 로그인 한 상태라면: 비공개여부가 true라도, 내 꺼는 보이게 해야 함...

        return new ApiResponse<>(HttpStatus.OK.value(), "해당 프로필을 불러왔습니다.", profileService.getProfile(profileId, session));
    }

    //프로필 생성
    @PostMapping("/myprofile") //뭐져 왜 F 자동으로 안 들어가져
    public ApiResponse<String> createProfile(@RequestBody ProfileRequest profileRequest, HttpSession session) { //파라미터 추후에 변경
        log.info("프로필 만들기~! ");

//        int result = profileService.createProfile(profileRequest, session);

//        if (result == 200) return new ResponseEntity<>("프로필 생성 성공", HttpStatus.OK);
//        else return new ResponseEntity<>("프로필 생성 실패!", HttpStatus.INTERNAL_SERVER_ERROR);

        return new ApiResponse<>(HttpStatus.OK.value(), "프로필을 성공적으로 생성했습니다.", profileService.createProfile(profileRequest, session));
    }

    //프로필 삭제
    @DeleteMapping("/myprofile")
    public ApiResponse<String> removeProfile(@RequestParam(name = "profile_id") Long profileId, HttpSession session) {
        log.info("프로필 삭제하기!");

//        int canRemove = profileService.canRemoveProfile(profileId, session);
//        if( canRemove == 200 ) return new ResponseEntity<>("프로필 삭제 성공 "+profileId, HttpStatus.OK);
//        else return new ResponseEntity<>("프로필 삭제 실패 "+profileId, HttpStatus.INTERNAL_SERVER_ERROR);

        return new ApiResponse<>(HttpStatus.OK.value(), "프로필을 성공적으로 삭제했습니다.", profileService.canRemoveProfile(profileId, session));
    }

    //프로필 수정
    @PutMapping("/myprofile")
    public ApiResponse<String> modifyProfile(@RequestParam(name = "profile_id") Long profileId, HttpSession session,
                                              @RequestBody ProfileRequest profileRequest)
    {
        log.info("프로필 수정하기~! ");

//        int result = profileService.updateProfile(profileId, profileRequest, session);
//
//        if (result == 200) return new ResponseEntity<>("프로필 수정 성공", HttpStatus.OK);
//        else return new ResponseEntity<>("프로필 수정 실패!", HttpStatus.INTERNAL_SERVER_ERROR);

        return new ApiResponse<>(HttpStatus.OK.value(), "프로필을 성공적으로 수정했습니다.", profileService.updateProfile(profileId, profileRequest, session));
    }

}
