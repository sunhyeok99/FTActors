package com.a602.actors.domain.profile.controller;

import com.a602.actors.domain.profile.entity.Profile;
import com.a602.actors.domain.profile.service.ProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/profile")
@Slf4j
public class ProfileController {
    private final ProfileService profileService;

    @GetMapping("/list")
    public ResponseEntity<List<Profile>> getAllProfileList(@RequestParam(name = "sort") int sorting) {
        log.info("배우,감독 프로필 전체 목록");

        List<Profile> profiles = profileService.getProfileList(sorting);

        return new ResponseEntity<>(profiles, HttpStatus.OK);
    }

    @GetMapping("/detail")
    public ResponseEntity<Profile> getDetailProfile(@RequestParam(name = "member_id") Long memberId) {
        log.info("프로필 상세 페이지");

        Profile profile = profileService.getProfile(memberId);

        return new ResponseEntity<>(profile, HttpStatus.OK);
    }
}
