package com.a602.actors.domain.montage.service;


import com.a602.actors.domain.member.repository.MemberRepository;
import com.a602.actors.domain.montage.dto.MontageDto;
import com.a602.actors.domain.montage.dto.MontageReportDto;
import com.a602.actors.domain.montage.entity.Montage;
import com.a602.actors.domain.montage.entity.Report;
import com.a602.actors.domain.montage.repository.MontageRepository;

import com.a602.actors.global.common.config.FileUtil;
import com.a602.actors.global.common.enums.FolderType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// S3 연결
@Service
@Slf4j
public class MontageFileService {

    private final MontageRepository montageRepository;

    public MontageFileService(MontageRepository montageRepository) {
        this.montageRepository = montageRepository;
    }
    public List<MontageDto.Montages> getAllMontageList(){
        return montageRepository.getAllMontages();
    }

    public List<MontageDto.Montages> getMyMontage(Long memberId){
        return montageRepository.getMyMontages(memberId).stream().map(MontageDto.Montages::toDto).toList();
    }


    public String uploadFile(MultipartFile multipartFile) throws IOException {
        String originalFilename = multipartFile.getOriginalFilename();

        String savedName = FileUtil.makeFileName(originalFilename);

        String url = FileUtil.uploadFile(multipartFile, savedName, FolderType.MONTAGE_PATH);
        System.out.println("URL : " + url);

        montageRepository.saveMontage(originalFilename, savedName, url);

        return "";
    }

    public String deleteFile(Long montageId) throws IOException {
        Montage montage = montageRepository.getMontage(montageId);
        FileUtil.deleteFile(montage.getTitle(), FolderType.MONTAGE_PATH);

        montageRepository.deleteMontage(montageId);

        return "";
    }

    public boolean pushLike(Long montageId){
        Long memberId = 1L;
        return montageRepository.addLike(montageId, memberId);
    }

    // FIX : file null exception 처리
    public String report(MontageReportDto.CreateReport req,
                         MultipartFile file,
                         Long montageId) throws IOException {

        Long reporterId = 1L;
        // FIX : 자기 자신은 신고 못하는 에러 처리

        //MemberRepository.findByNickname();
        System.out.println("HELLO");
        String savedName = FileUtil.makeFileName(file.getOriginalFilename());

        // FIX : 파일 이름이 길 경우
        //if(savedName.length() >= 255) throw 파일 이름이 너무 깁니다. -> 아니면 프론트가 처리하도록 하자
        String url = FileUtil.uploadFile(file, savedName, FolderType.REPORT_PATH);
        montageRepository.addReport(1L, montageId, req.getReason(), url);

        return "";
    }


}
