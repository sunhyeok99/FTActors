package com.a602.actors.domain.montage.controller;

import com.a602.actors.domain.montage.dto.MontageCommentDto;
import com.a602.actors.domain.montage.dto.MontageDto;
import com.a602.actors.domain.montage.dto.MontageReportDto;
import com.a602.actors.domain.montage.service.MontageCommentService;
import com.a602.actors.domain.montage.service.MontageFileService;
import com.a602.actors.global.common.dto.ApiResponse;
import com.a602.actors.global.exception.FileException;
import com.a602.actors.global.exception.MontageException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
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
    public ApiResponse<String> uploadMontage(@RequestPart(value = "file", required = true) MultipartFile file)
            throws GeneralSecurityException, IOException, MontageException, FileException {
        log.info(file.getOriginalFilename());
        log.info("INFO : {}", file.isEmpty());
        return new ApiResponse<>(HttpStatus.OK.value(), "성공적으로 업로드되었습니다.", montageFileService.uploadFile(file));
    }

    @DeleteMapping("/{montageId}")
    public ApiResponse<String> deleteMontage(@PathVariable("montageId") Long montageId) throws IOException {
        log.info("MONTAGE ID : " + montageId);
        return new ApiResponse<>(HttpStatus.OK.value(), "파일 삭제를 성공적으로 수행했습니다.", montageFileService.deleteFile(montageId));
    }
    @GetMapping("/my-montage")
    public ApiResponse<List<MontageDto.Montages>> getMyMontage(){
        Long memberId = 1L;
        return new ApiResponse<>(HttpStatus.OK.value(), "성공적으로 반환했습니다", montageFileService.getMyMontage(memberId));
    }
    @GetMapping("/{montageId}/comment")
    public ApiResponse<List<MontageCommentDto.Response>> getAllComments(@PathVariable("montageId") Long montageId){
        //return null;
        log.info("GET COMMENT LIST ENTER");
        return new ApiResponse<>(HttpStatus.OK.value(), "모든 댓글을 불러왔습니다.", montageCommentService.getAllComments(montageId));
    }

    @PostMapping("/{montageId}/comment")
    public ApiResponse<String> writeComment(@RequestBody MontageCommentDto.CreateRequest req){
        //return null;
        log.info("ENTER");
        return new ApiResponse<>(HttpStatus.CREATED.value(), "댓글을 작성했습니다.", montageCommentService.writeComment(req));
    }

    @PatchMapping("/{montageId}/comment")
    public ApiResponse<String> updateComment(@RequestBody MontageCommentDto.UpdateRequest req){
        return new ApiResponse<>(HttpStatus.OK.value(), "댓글을 수정했습니다.", montageCommentService.updateComment(req));
    }

    @DeleteMapping("/{montageId}/comment/{commentId}")
    public ApiResponse<String> deleteComment(@PathVariable("montageId") Long montageId, @PathVariable("commentId") Long commentId){
        return new ApiResponse<>(HttpStatus.OK.value(), "댓글을 삭제했습니다.", montageCommentService.deleteComment(montageId, commentId));
    }

    @PostMapping("/{montageId}/like")
    public ApiResponse<String> likeMontage(@PathVariable("montageId") Long montageId){

        boolean result = montageFileService.pushLike(montageId);

        if(result)
            return new ApiResponse<>(HttpStatus.OK.value(), "좋아요를 눌렀습니다.", "");
        else
            return new ApiResponse<>(HttpStatus.OK.value(), "좋아요를 해제했습니다.", "");
    }

    // 몽타주 아이디를 보내는데 굳이 reportee 아이디를 보낼 필요가 없다.
    @PostMapping("/{montageId}/report")
    public ApiResponse<String> reportMontage(@RequestPart(name="req") MontageReportDto.CreateReport req,
                                             @RequestPart(name="file") MultipartFile file,
                                             @PathVariable("montageId") Long montageId) throws IOException {

        System.out.println("HELLO");
        return new ApiResponse<>(HttpStatus.OK.value(), "신고를 눌렀습니다.", montageFileService.report(req, file, montageId));

    }

    @PostMapping("/{montageId}/comment/{commentId}")
    public ApiResponse<String> reportComment(@RequestPart(name="req") MontageReportDto.CreateReport req,
                                             @RequestPart(name="file") MultipartFile file,
                                             @PathVariable("montageId") Long montageId,
                                             @PathVariable("commentId") Long commentId) throws IOException {
        return new ApiResponse<>(HttpStatus.OK.value(), "신고를 눌렀습니다.", montageCommentService.report(req, file, montageId, commentId));
    }

}
