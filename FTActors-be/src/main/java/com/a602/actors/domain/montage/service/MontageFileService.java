package com.a602.actors.domain.montage.service;


import com.a602.actors.domain.montage.dto.MontageDto;
import com.a602.actors.domain.montage.entity.Montage;
import com.a602.actors.domain.montage.repository.MontageRepository;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

//    public List<MontageDto.MontageInfo> getMyMontage(Integer memberId){
//        //return montageRepository.findByMemberId(memberId).stream().map(MontageDto.MontageInfo::toDto).toList();
//        return null;
//    }


    public String uploadFile(MultipartFile multipartFile) throws IOException {
        String originalFilename = "montages/" + multipartFile.getOriginalFilename();

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(multipartFile.getSize());
        metadata.setContentType(multipartFile.getContentType());

//        amazonS3.putObject(bucket, originalFilename, multipartFile.getInputStream(), metadata);
//        String url = amazonS3.getUrl(bucket, originalFilename).toString();

        Montage data = Montage.builder()
                .title(originalFilename)
                .link("")
                .build();

        montageRepository.saveMontage("", "");
        return "";
    }

}
