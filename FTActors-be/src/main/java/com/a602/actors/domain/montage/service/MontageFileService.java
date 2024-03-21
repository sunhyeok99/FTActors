package com.a602.actors.domain.montage.service;


import com.a602.actors.domain.montage.dto.MontageDto;
import com.a602.actors.domain.montage.entity.Montage;
import com.a602.actors.domain.montage.repository.MontageRepository;

import com.a602.actors.global.common.config.FileUtil;
import com.a602.actors.global.common.enums.FolderType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
        return montageRepository.getAllMontages().stream().map(MontageDto.Montages::toDto).toList();
    }

    public List<MontageDto.Montages> getMyMontage(Long memberId){
        return montageRepository.getMyMontages(memberId).stream().map(MontageDto.Montages::toDto).toList();
    }


    public String uploadFile(MultipartFile multipartFile) throws IOException {
        String originalFilename = multipartFile.getOriginalFilename();

        String url = FileUtil.uploadFile(multipartFile, FolderType.MONTAGE_PATH);
        System.out.println("URL : " + url);

        montageRepository.saveMontage(originalFilename, url);
        return "";
    }

    public String deleteFile(Long montageId) throws IOException {

        Montage montage = montageRepository.getMontage(montageId);
        FileUtil.deleteFile(montage.getTitle(), FolderType.MONTAGE_PATH);

        montageRepository.deleteMontage(montageId);

        return "";
    }
}
