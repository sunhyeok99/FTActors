//package com.a602.actors.domain.profile.service;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.*;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//@Service
//@RequiredArgsConstructor
//@Slf4j
//public class LogstashService {
//    private final RestTemplate restTemplate;
//
////    public LogstashService(RestTemplate restTemplate) {
////        this.restTemplate = restTemplate;
////    }
//
//    //삭제 요청 보내기 (요청만 보내기 때문에, 실제 삭제하는 건 따로 구현해야 함)
//    public void sendDeleteRequest(Long id) {
//        log.info("들어는 왔단다");
//        // Logstash HTTP input의 URL
//        String logstashUrl = "http://localhost:8080/api"; //아 뭔데
//
//        // HTTP 요청 헤더 설정 {{엘라스틱 서치는 명령을 Json 형식으로 함}}
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        // 삭제할 데이터 ID를 포함한 JSON 데이터 생성 {"id": 입력된아이디값}
//        String json = "{\"id\": " + id + "}";
//
//        // HTTP 요청 본문 설정 {{본문에 json(실제 명령어가 있는 곳), header(어느 문서에 명령을 실행할지 지정)을 넣음}}
//        HttpEntity<String> request = new HttpEntity<>(json, headers);
//        System.out.println("request 값은: "+ request);
//
//        // HTTP DELETE 요청 보내기
//        ResponseEntity<String> response = restTemplate.exchange(logstashUrl, HttpMethod.DELETE, request, String.class);
//        System.out.println("response 값은: "+response);
//
//        // HTTP 응답 확인
////        if (response.getStatusCode() == HttpStatus.OK) {
//        if (response.getStatusCode().is2xxSuccessful()) {
//            System.out.println("Data deleted successfully.");
//        } else {
//            System.err.println("Failed to delete data. Response: " + response.getBody() + " and header is: " +response.getStatusCode());
//        }
//    }
//}
