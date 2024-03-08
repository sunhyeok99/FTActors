package com.a602.actors.domain.montage.controller;

import com.a602.actors.domain.montage.dto.MontageDto;
import com.a602.actors.domain.montage.service.MontageFileService;
import com.a602.actors.global.common.dto.ApiResponse;
import lombok.extern.slf4j.Slf4j;
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

    public MontageController(MontageFileService montageFileService){
        this.montageFileService = montageFileService;
    }

    @GetMapping("/test")
    public String getTest(){
        return "서버 정상적으로 열렸어요!";
    }
//
    @GetMapping("/list")
    public ApiResponse<List<MontageDto.MontageInfo>> getMontageList() throws GeneralSecurityException, IOException {
        return new ApiResponse<>(200, "성공적으로 반환했습니다", montageFileService.getAllMontageList());
    }

    @PostMapping("/upload")
    public ApiResponse<String> uploadMontageList(@RequestParam(value = "file") MultipartFile file) throws GeneralSecurityException, IOException {

        log.info(file.getOriginalFilename());

        return new ApiResponse<String>(200, "message", montageFileService.uploadFile(file));
    }
}
