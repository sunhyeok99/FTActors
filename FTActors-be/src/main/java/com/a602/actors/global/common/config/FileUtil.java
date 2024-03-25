package com.a602.actors.global.common.config;

import com.a602.actors.domain.montage.entity.Montage;
import com.a602.actors.global.common.enums.FolderType;
import com.a602.actors.global.exception.ExceptionCodeSet;
import com.a602.actors.global.exception.FileException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

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

    public static String makeFileName(String fileName) throws FileException{

        UUID uuid = UUID.randomUUID();

        String savedName = uuid.toString() + "_" + fileName;
        if(savedName.length() >= 50)
            throw new FileException(ExceptionCodeSet.FILE_NAME_TOO_LONG);
        return savedName;
    }

    // S3 링크 반환
    public static String uploadFile(MultipartFile multipartFile, String savedName, FolderType folderType) throws IOException, FileException {

        if(multipartFile == null || multipartFile.isEmpty())
            throw new FileException(ExceptionCodeSet.FILE_NOT_EXISTS);

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(multipartFile.getSize());
        metadata.setContentType(multipartFile.getContentType());

        amazonS3.putObject(bucket, folderType.getPath() + savedName, multipartFile.getInputStream(), metadata);
        String url = amazonS3.getUrl(bucket, folderType.getPath() + savedName).toString();

        return url;
    }

    public static String deleteFile(String fileName, FolderType folderType) throws IOException{

        try{
            // 삭제할 게 없으면 에러가 안 뜨네?
            amazonS3.deleteObject(bucket, folderType.getPath() + fileName);
        }
        catch (SdkClientException e){
            log.info("ERROR -- path : {} , message : {}", folderType.getPath() + fileName, e.getMessage());
            throw new IOException("ERROR deleting file from S3", e);
        }

        return "";
    }

}
