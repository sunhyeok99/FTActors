package com.a602.actors.domain.montage.controller;

import com.a602.actors.domain.montage.dto.MontageCommentDto;
import com.a602.actors.domain.montage.dto.MontageDto;
import com.a602.actors.domain.montage.service.MontageCommentService;
import com.a602.actors.domain.montage.service.MontageFileService;
import com.a602.actors.global.common.dto.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/montage")
public class MontageController {

    private final MontageFileService montageFileService;
    private final MontageCommentService montageCommentService;

    public MontageController(MontageFileService montageFileService,
                             MontageCommentService montageCommentService){
        this.montageFileService = montageFileService;
        this.montageCommentService = montageCommentService;
    }

    @GetMapping("/test")
    public String getTest(){
        return "서버 정상적으로 열렸어요!";
    }
//
    @GetMapping("/list")
    public ApiResponse<List<MontageDto.Montages>> getMontageList() throws GeneralSecurityException, IOException {
        return new ApiResponse<>(HttpStatus.OK.value(), "몽타주 리스트를 불러왔습니다.", montageFileService.getAllMontageList());
    }

    @PostMapping("/upload")
    public ApiResponse<String> uploadMontageList(@RequestParam(value = "file") MultipartFile file) throws GeneralSecurityException, IOException {
        log.info(file.getOriginalFilename());
        return new ApiResponse<>(HttpStatus.OK.value(), "성공적으로 업로드되었습니다.", montageFileService.uploadFile(file));
    }
//
//    @GetMapping("/my-montage")
//    public ApiResponse<List<MontageDto.MontageInfo>> getMyMontage(){
//        Integer memberId = 1;
//        return new ApiResponse<>(HttpStatus.OK.value(), "성공적으로 반환했습니다", montageFileService.getMyMontage(memberId));
//    }
//
//
    @GetMapping("/{montageId}/comment")
    public ApiResponse<List<MontageCommentDto.Response>> getAllComments(@PathVariable("montageId") Long montageId){
        //return null;
        return new ApiResponse<>(HttpStatus.OK.value(), "모든 댓글을 불러왔습니다.", montageCommentService.getAllComments(montageId));
    }

    @PostMapping("/{montageId}/comment")
    public ApiResponse<String> writeComment(@RequestBody MontageCommentDto.Request req){
        //return null;
        return new ApiResponse<>(HttpStatus.CREATED.value(), "댓글을 작성했습니다.", montageCommentService.writeComment(req));
    }

    @PatchMapping("/{montageId}/comment")
    public ApiResponse<String> updateComment(@RequestBody MontageCommentDto.Request req){
        return new ApiResponse<>(HttpStatus.OK.value(), "댓글을 수정했습니다.", montageCommentService.updateComment(req));
    }

    @DeleteMapping("/{montageId}/comment")
    public ApiResponse<String> deleteComment(@PathVariable("montageId") Long montageId){
        return new ApiResponse<>(HttpStatus.OK.value(), "댓글을 삭제했습니다.", montageCommentService.deleteComment(montageId));
    }
}
