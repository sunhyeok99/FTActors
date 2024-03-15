package com.a602.actors.global.common.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class S3Config {

    @Value("${cloud.aws.credentials.access-key}")
    private static String accessKey;
    @Value("${cloud.aws.credentials.access-key}")
    public void setAccessKey(String key){
        S3Config.accessKey = key;
    }

    @Value("${cloud.aws.credentials.secret-key}")
    private static String secretKey;
    @Value("${cloud.aws.credentials.secret-key}")
    public void setSecretKey(String key){
        S3Config.secretKey = key;
    }

    @Value("${cloud.aws.region.static}")
    private static String region;
    @Value("${cloud.aws.region.static}")
    public void setRegion(String region){
        S3Config.region = region;
    }

    @Bean
    public AmazonS3Client amazonS3Client() {

        //log.info("AK : {}, SK : {}", accessKey, secretKey);
        log.info("region : {} ", region);
        BasicAWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);

        return (AmazonS3Client) AmazonS3ClientBuilder
                .standard()
                .withRegion(region)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();
    }
}
