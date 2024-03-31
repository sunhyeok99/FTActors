package com.a602.actors.domain.recommend.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RecommendService {
//    private final RestTemplate restTemplate;

//    public RecommnedService(RestTemplateBuilder builder){
//        restTemplate = builder.build();
//    }

//    @Autowired
//    public RecommendService(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }

    // flask 서버와 통신
    public String test(){
        HttpHeaders httpHeaders = new HttpHeaders();
        //httpHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        // HttpEntity에 헤더 및 params 설정
        HttpEntity entity = new HttpEntity(httpHeaders);

        // RestTemplate의 exchange 메소드를 통해 URL에 HttpEntity와 함께 요청
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:5000/test", HttpMethod.GET,
                entity, String.class);

        // 요청 후 응답 확인
        System.out.println(responseEntity.getStatusCode());
        System.out.println(responseEntity.getBody());

        return responseEntity.getBody();
    }

}