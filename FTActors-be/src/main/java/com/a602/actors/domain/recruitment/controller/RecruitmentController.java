package com.a602.actors.domain.recruitment.controller;

import com.a602.actors.domain.recruitment.dto.RecruitmentRequestDto;
import com.a602.actors.domain.recruitment.dto.RecruitmentResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/recruitment")
@RestController
public interface RecruitmentController {

    // 1. 공고 등록
    @PostMapping("")
    ResponseEntity<Void> register(@RequestBody RecruitmentRequestDto recruitmentRequestDto);

    @DeleteMapping("")
    ResponseEntity<Void> remove(@RequestParam(value = "recruitmentId") Long recruitmentId);

    @PutMapping("update")
    ResponseEntity<Void> update(@RequestBody RecruitmentRequestDto recruitmentRequestDto);

    @PutMapping("updateDate")
    ResponseEntity<Void> updateDate(@RequestParam Long recruitmentId, String endDate);

    @GetMapping("list")
    ResponseEntity<List<RecruitmentResponseDto>> getList();

    @GetMapping("detail")
    ResponseEntity<RecruitmentResponseDto> getDetail(@RequestParam Long recruitmentId);

    @GetMapping("postList")
    ResponseEntity<List<RecruitmentResponseDto>> getPostList(@RequestParam Long memberId);

    // 찜 등록/해제
//    @PutMapping("wishlist")
//    ResponseEntity<Void> updateLike(@RequestParam Long recruitmentId, @RequestParam Long memberId);

//    @GetMapping("wishlist")
//    ResponseEntity<List>



}
