package com.a602.actors.domain.recruitment.controller;

import com.a602.actors.domain.apply.service.ApplyService;
import com.a602.actors.domain.recruitment.dto.RecruitmentListResponseDto;
import com.a602.actors.domain.recruitment.dto.RecruitmentRequestDto;
import com.a602.actors.domain.recruitment.dto.RecruitmentResponseDto;
import com.a602.actors.domain.recruitment.service.RecruitmentService;
import com.a602.actors.domain.recruitment.service.WishlistService;
import com.a602.actors.global.common.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/recruitment")
@RestController
public class RecruitmentController {

    private final RecruitmentService recruitmentService;
    private final WishlistService wishlistService;
    private final ApplyService applyService;

    @PostMapping("")
    public ApiResponse<String> register(RecruitmentRequestDto recruitmentRequestDto) {
        recruitmentService.register(recruitmentRequestDto);
        return new ApiResponse<>(HttpStatus.OK.value(), "공고를 등록하였습니다.","");
    }

    @DeleteMapping("")
    public ApiResponse<String> remove(@RequestParam Long recruitmentId) {
        recruitmentService.remove(recruitmentId);
        return new ApiResponse<>(HttpStatus.OK.value(), "공고를 제거하였습니다.","");
    }

    @PutMapping("update")
    public ApiResponse<String> update(@RequestBody RecruitmentRequestDto recruitmentRequestDto) {
        recruitmentService.update(recruitmentRequestDto);
        return new ApiResponse<>(HttpStatus.OK.value(), "공고를 업데이트하였습니다.","");
    }

    @PutMapping("updateDate")
    public ApiResponse<String> updateDate(@RequestParam Long recruitmentId, @RequestParam String endDate) {
        recruitmentService.updateDate(recruitmentId , endDate);
        return new ApiResponse<>(HttpStatus.OK.value(), "공고 마감날자를 변경하였습니다.","");
    }

    @GetMapping("list")
    public ApiResponse<List<RecruitmentListResponseDto>> getList(@RequestParam Long memberId) {
        return new ApiResponse<>(HttpStatus.OK.value(), "공고 리스트를 불러왔습니다.", recruitmentService.getList(memberId));
    }

    @GetMapping("detail")
    public ApiResponse<RecruitmentResponseDto> getDetail(@RequestParam Long recruitmentId, @RequestParam Long memberId) {
        return new ApiResponse<>(HttpStatus.OK.value(), "공고 내용을 불러왔습니다.", recruitmentService.getDetail(recruitmentId, memberId));
    }

    @GetMapping("postList")
    public ApiResponse<List<RecruitmentListResponseDto>> getPostList(@RequestParam Long postMemberId) {
        return new ApiResponse<>(HttpStatus.OK.value(), "해당 유저가 게시한 공고를 불러왔습니다.", recruitmentService.registerList(postMemberId));
    }

    // 찜등록 / 찜 삭제
    @PutMapping("wishlist")
    public ApiResponse<Integer> wishList(@RequestParam Long recruitmentId, @RequestParam Long memberId){
        return new ApiResponse<>(HttpStatus.OK.value(), "찜 변경 성공", wishlistService.wishlist(recruitmentId, memberId));
    }
    // 찜한 공고 리스트
    @GetMapping("wishlist")
    public ApiResponse<List<RecruitmentListResponseDto>> getWishList(@RequestParam Long memberId){
        return new ApiResponse<>(HttpStatus.OK.value(), "찜한 공고 리스트를 불러왔습니다", wishlistService.list(memberId));
    }

    // 지원하기
//    @PostMapping("apply")
//    public ApiResponse<Void> apply(@RequestBody applyDto){
////        applyService
//        return new ApiResponse<>(HttpStatus.OK.value(), "지원에 성공하였습니다");
//    }
//    // 지원취소
//    @DeleteMapping("apply")
//    public ApiResponse<Void> applyCancel(@RequestParam Long recruitmentId, @RequestParam Long memberId){
////
//        return new ApiResponse<>(HttpStatus.OK.value(), "지원 취소하였습니다");
//    }
//    // 지원상세내역
//    @GetMapping("applyDetail")
//    public ApiResponse<Apply> applyDetail(@RequestParam Long recruitmentId, @RequestParam Long memberId){
//        return new ApiResponse<>(HttpStatus.OK.value(), "지원 상세내역을 불러왔습니다", applyService.);
//    }
//
//    // 지원 내역 리스트
//    @GetMapping("applyList")
//    public ApiResponse<List<Apply>> applyList(@RequestParam Long memberId){
//        return new ApiResponse<>(HttpStatus.OK.value(), "지원 내역을 불러왔습니다", applyService.);
//    }
}
