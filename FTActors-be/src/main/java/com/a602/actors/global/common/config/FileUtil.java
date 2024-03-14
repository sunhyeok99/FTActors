package com.a602.actors.global.common.config;

import com.a602.actors.domain.montage.entity.Montage;
import com.a602.actors.global.common.enums.FolderType;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
@Slf4j
public class FileUtil {

    @Value("${cloud.aws.s3.bucket}")
    private static String bucket;
    @Value("${cloud.aws.s3.bucket}")
    public void setBucket(String bucketName) {
        bucket = bucketName;
    }
    private static AmazonS3 amazonS3;

    public FileUtil(AmazonS3 amazonS3){
        FileUtil.amazonS3 = amazonS3;
    }

    // S3 링크 반환
    public static String uploadFile(MultipartFile multipartFile, FolderType folderType) throws IOException {
        String originalFilename = multipartFile.getOriginalFilename();

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(multipartFile.getSize());
        metadata.setContentType(multipartFile.getContentType());

        amazonS3.putObject(bucket, folderType.getPath() + originalFilename, multipartFile.getInputStream(), metadata);
        String url = amazonS3.getUrl(bucket, folderType.getPath() + originalFilename).toString();

        return url;
    }

}
