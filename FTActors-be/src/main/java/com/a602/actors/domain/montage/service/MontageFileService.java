package com.a602.actors.domain.montage.service;


import com.a602.actors.domain.member.repository.MemberRepository;
import com.a602.actors.domain.montage.dto.MontageDto;
import com.a602.actors.domain.montage.dto.MontageReportDto;
import com.a602.actors.domain.montage.entity.Montage;
import com.a602.actors.domain.montage.entity.Report;
import com.a602.actors.domain.montage.repository.MontageRepository;

import com.a602.actors.global.common.config.FileUtil;
import com.a602.actors.global.common.enums.FolderType;
import com.a602.actors.global.exception.ExceptionCodeSet;
import com.a602.actors.global.exception.FileException;
import com.a602.actors.global.exception.MontageException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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


    public String uploadFile(MultipartFile multipartFile) throws IOException, FileException {
        String originalFilename = multipartFile.getOriginalFilename();
        String savedName = FileUtil.makeFileName(originalFilename);

        log.info("FILE INFO : {}", multipartFile);

        String url = FileUtil.uploadFile(multipartFile, savedName, FolderType.MONTAGE_PATH);
        System.out.println("URL : " + url);

        montageRepository.saveMontage(originalFilename, savedName, url);

        return "";
    }

    public String deleteFile(Long montageId) throws IOException, FileException {
        Montage montage = montageRepository.getMontage(montageId)
                .orElseThrow(() -> new MontageException(ExceptionCodeSet.ENTITY_NOT_EXISTS));

        FileUtil.deleteFile(montage.getSavedName(), FolderType.MONTAGE_PATH);

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
                         Long montageId) throws IOException, MontageException, FileException {

        Long reporterId = 1L;

        // 파일이 없는 경우
        if(file == null){
            throw new MontageException(ExceptionCodeSet.FILE_NOT_EXISTS);
        }
        
        // montageId에 맞는 데이터가 없으면 예외 처리
        Montage montage = montageRepository.getMontage(montageId)
                        .orElseThrow(() -> new MontageException(ExceptionCodeSet.ENTITY_NOT_EXISTS));

        // 자기 자신은 신고 불가
        if(Objects.equals(montage.getMember().getId(), reporterId)){
            throw new MontageException(ExceptionCodeSet.SELF_REPORT);
        }

        //MemberRepository.findByNickname();
        System.out.println("HELLO");
        String savedName = FileUtil.makeFileName(file.getOriginalFilename());

        String url = FileUtil.uploadFile(file, savedName, FolderType.REPORT_PATH);
        log.info("URL : {}", url);
        montageRepository.addReport(1L, montageId, req.getReason(), url);

        return "";
    }


}
