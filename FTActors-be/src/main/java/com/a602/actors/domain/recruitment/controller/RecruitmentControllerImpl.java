package com.a602.actors.domain.recruitment.controller;

import com.a602.actors.domain.apply.service.ApplyService;
import com.a602.actors.domain.recruitment.dto.RecruitmentRequestDto;
import com.a602.actors.domain.recruitment.dto.RecruitmentResponseDto;
import com.a602.actors.domain.recruitment.service.RecruitmentService;
import com.a602.actors.domain.wishlist.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/recruitment")
@RestController
public class RecruitmentControllerImpl implements RecruitmentController {

    private final RecruitmentService recruitmentService;
    private final WishlistService wishlistService;
    private final ApplyService applyService;

    @Override
    @PostMapping("")
    public ResponseEntity<Void> register(RecruitmentRequestDto recruitmentRequestDto) {
        recruitmentService.register(recruitmentRequestDto);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @Override
    @DeleteMapping("")
    public ResponseEntity<Void> remove(@RequestParam  Long recruitmentId) {
        System.out.println("!!!!!!!!!!!!!!!!!!!!");
        recruitmentService.remove(recruitmentId);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @Override
    @PutMapping("update")
    public ResponseEntity<Void> update(@RequestBody RecruitmentRequestDto recruitmentRequestDto) {
        recruitmentService.update(recruitmentRequestDto);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @Override
    @PutMapping("updateDate")
    public ResponseEntity<Void> updateDate(@RequestParam Long recruitmentId, @RequestParam String endDate) {
        recruitmentService.updateDate(recruitmentId , endDate);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @Override
    @GetMapping("list")
    public ResponseEntity<List<RecruitmentResponseDto>> getList() {
    List<RecruitmentResponseDto> list = recruitmentService.getList();
        return new ResponseEntity<>(list , HttpStatus.OK);
    }

    @Override
    @GetMapping("detail")
    public ResponseEntity<RecruitmentResponseDto> getDetail(@RequestParam Long recruitmentId) {
        RecruitmentResponseDto detail = recruitmentService.getDetail(recruitmentId);
        return new ResponseEntity<>(detail , HttpStatus.OK);
    }

    @Override
    @GetMapping("postList")
    public ResponseEntity<List<RecruitmentResponseDto>> getPostList(@RequestParam Long memberId) {
        List<RecruitmentResponseDto> list = recruitmentService.registerList(memberId);
        return new ResponseEntity<>(list , HttpStatus.OK);
    }

}
