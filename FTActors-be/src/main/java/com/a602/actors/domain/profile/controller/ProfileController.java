package com.a602.actors.domain.profile.controller;

import com.a602.actors.domain.profile.entity.Profile;
import com.a602.actors.domain.profile.service.ProfileService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/profile")
@Slf4j
public class ProfileController {
    private final ProfileService profileService;

    @GetMapping("/list")
    public ResponseEntity<List<Profile>> getAllProfileList(@RequestParam(name = "sort") int sorting, @RequestParam(name = "condition") Character condition) {
        log.info("배우,감독 프로필 전체 목록 - 컨트롤러");

        List<Profile> profiles = profileService.getProfileList(sorting, condition); //null 가능

        return new ResponseEntity<>(profiles, HttpStatus.OK);
    }

    @GetMapping("/detail")
    public ResponseEntity<Profile> getDetailProfile(@RequestParam(name = "member_id") Long memberId, @RequestParam(name = "condition") Character condition) {
        log.info("프로필 상세 페이지");

        Profile profile = profileService.getProfile(memberId, condition); //null 가능

        return new ResponseEntity<>(profile, HttpStatus.OK);
    }

    //프로필 만드는 거 ?? -> 이거 자기소개는 여기서 쓰게 해야할 거 같은뎅...
    //비공개 설정은 회원에서 하는거낙>?

    //프로필 생성
//    @PostMapping("/myprofile")
//    public ResponseEntity<?> createProfile(@RequestParam(name = "member_id") Long memberId,
//                                           @RequestBody String selfIntroduction) { //파라미터 추후에 변경
//        log.info("프로필 만들기~! (자기소개만 작성, 나머지는 회원 db에서 가져오기)");
//
//
//
//        return new ResponseEntity<>(, HttpStatus.OK);
//    }
//
    //프로필 삭제
    @DeleteMapping("/myprofile")
    public ResponseEntity<String> removeProfile(@RequestParam(name = "profile_id") Long profileId, HttpSession session) {
        log.info("프로필 삭제하기!");

        int canRemove = profileService.canRemoveProfile(profileId, session);
        if( canRemove == 200 ) return new ResponseEntity<>("프로필 삭제 성공 "+profileId, HttpStatus.OK);
        else return new ResponseEntity<>("프로필 삭제 실패 "+profileId, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    //프로필 수정
//    @PutMapping("/myprofile")

    //프로필 공개-비공개 수정
//    @PutMapping("/puclicprivate")
}
