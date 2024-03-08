package com.a602.actors.domain.montage.service;


import com.a602.actors.domain.montage.dto.MontageDto;
import com.a602.actors.domain.montage.entity.Montage;
import com.a602.actors.domain.montage.repository.MontageRepository;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ListObjectsV2Request;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3ObjectSummary;
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

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    private final AmazonS3 amazonS3;
    private final MontageRepository montageRepository;

    public MontageFileService(AmazonS3 amazonS3, MontageRepository montageRepository) {
        this.amazonS3 = amazonS3;
        this.montageRepository = montageRepository;
    }
    public List<MontageDto.MontageInfo> getAllMontageList(){

        List<MontageDto.MontageInfo> montagesList = montageRepository.findAll().stream().map(MontageDto.MontageInfo::toDto).toList();
        return montagesList;
    }

//    public ResponseEntity<UrlResource> downloadImage(String originalFilename) {
//        UrlResource urlResource = new UrlResource(amazonS3.getUrl(bucket, originalFilename));
//
//        String contentDisposition = "attachment; filename=\"" +  originalFilename + "\"";
//
//        // header에 CONTENT_DISPOSITION 설정을 통해 클릭 시 다운로드 진행
//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
//                .body(urlResource);
//    }

    public String uploadFile(MultipartFile multipartFile) throws IOException {
        String originalFilename = "videos/" + multipartFile.getOriginalFilename();

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(multipartFile.getSize());
        metadata.setContentType(multipartFile.getContentType());

        amazonS3.putObject(bucket, originalFilename, multipartFile.getInputStream(), metadata);
        String url = amazonS3.getUrl(bucket, originalFilename).toString();

        Montage data = Montage.builder()
                .title(originalFilename)
                .link(url)
                .likeCount(0)
                .build();

        montageRepository.save(data);
        return amazonS3.getUrl(bucket, originalFilename).toString();
    }

}
