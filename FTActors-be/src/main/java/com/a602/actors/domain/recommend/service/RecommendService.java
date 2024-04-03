package com.a602.actors.domain.recommend.service;


import com.a602.actors.domain.recruitment.dto.RecruitmentListResponseDto;
import com.a602.actors.domain.recruitment.entity.Recruitment;
import com.a602.actors.domain.recruitment.repository.RecruitmentRepository;
import com.a602.actors.global.jwt.util.JWTUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.mapstruct.ap.shaded.freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecommendService {
//    private RestTemplate restTemplate;
    private final RecruitmentRepository recruitmentRepository;
    private final JWTUtil jwtUtill;



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
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:5000/recommend", HttpMethod.POST,
                entity, String.class);

        // 요청 후 응답 확인
        System.out.println(responseEntity.getStatusCode());
        System.out.println(responseEntity.getBody());

        return responseEntity.getBody();
    }

    public List<?> getRecommendedRecruitment(Long memberId){
        RestTemplate restTemplate = new RestTemplate();
//        Long memberId = 1L;
        List<Recruitment> recruitmentList = recruitmentRepository.findWishlistOnly(memberId);
        List<Object[]> result = recruitmentRepository.findAllRecruitment(memberId);
        if(recruitmentList.isEmpty()) {
            // 최신순
            recruitmentList = recruitmentRepository.findAllByOrderByStartDateDesc();

            // 최신순으로 정렬
            recruitmentList.sort(Comparator.comparing(Recruitment::getStartDate).reversed());
            int size = Math.min(recruitmentList.size(), 10);
            if (size == 0) {
                return Collections.emptyList();
            } else {
                return recruitmentList.subList(0, size);
            }
        }
        else{
            // 찜 목록을 포함한 전체 공고
            List<RecruitmentListResponseDto> recruitmentListResponseDtos = result.stream()
                    .map(recruitments -> {
                        Recruitment recruitment = (Recruitment) recruitments[0];
                        int wishlist = 1;
                        if (recruitments[1] == null) {
                            wishlist = 0;
                        }
                        return RecruitmentListResponseDto.builder()
                                .id(recruitment.getId())
                                .title(recruitment.getTitle())
                                .content(recruitment.getContent())
                                .image(recruitment.getImage())
                                .endDate(recruitment.getEndDate())
                                .wishlist(wishlist)
                                .privateRecruitment(recruitment.getPrivateRecruitment())
                                .build();
                    })
                    .collect(Collectors.toList());
//            recruitmentListResponseDtos.get(0).getWishList();
//            System.out.println(recruitmentListResponseDtos.get(3).getWishList() + " " + recruitmentListResponseDtos.get(3).getTitle());

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            JSONObject jsonObject = new JSONObject();
            JSONArray jsonArray = new JSONArray();
//            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!>>>>> SIZE :::" + recruitmentListResponseDtos.size());
//            for (RecruitmentListResponseDto rec : recruitmentListResponseDtos) {
//                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>" + rec.toString());
//            }
            for (int i = 0; i < recruitmentListResponseDtos.size(); i++) {
                jsonObject = new JSONObject();
                jsonObject.put("id", recruitmentListResponseDtos.get(i).getId());
                jsonObject.put("title", recruitmentListResponseDtos.get(i).getTitle());
                jsonObject.put("content", recruitmentListResponseDtos.get(i).getContent());
                jsonObject.put("wishList", recruitmentListResponseDtos.get(i).getWishList());
//                System.out.println(recruitmentListResponseDtos.get(i).getTitle());
//                recruitmentListResponseDtos.get(i).
                jsonArray.put(jsonObject);
            }
//            for(int i=0; i<jsonArray.length(); i++){
//                System.out.println(jsonArray.get(i).toString());
//            }

//          요청 엔티티 생성
            HttpEntity<String> entity = new HttpEntity<>(jsonArray.toString(), headers);

            //http post 요청 보내기
            ResponseEntity<String> responseEntity = restTemplate.exchange("http://flask:5000/recommend", HttpMethod.POST, entity, String.class);


            // 요청 후 응답 확인
            System.out.println(responseEntity.getStatusCode());
            System.out.println(responseEntity.getBody());


//            JSONObject jsonResponse = new JSONObject(responseEntity.getBody());
//            JSONArray recommendedIds = jsonResponse.getJSONArray("id");
//            for(int i=0; i<recommendedIds.length(); i++){
//                System.out.println(recommendedIds.get(i));
//            }
            // 응답 결과 파싱
            JSONArray jsonResponseArray = new JSONArray(responseEntity.getBody());
            List<Recruitment> recommendedRecruitments = new ArrayList<>();
            for (int i = 0; i < jsonResponseArray.length(); i++) {
                jsonObject = jsonResponseArray.getJSONObject(i);
                Long recommendedId = (long) jsonObject.getInt("id");
                // 공고 ID를 사용하여 공고를 찾아 리스트에 추가
                Recruitment recruitment = recruitmentRepository.findById(recommendedId).orElse(null);
                if (recruitment != null) {
                    recommendedRecruitments.add(recruitment);
                }
            }
            // 추천된 공고 찾기
//            List<Recruitment> recommendedRecruitments = recruitmentRepository.findByIdIn(recommendedIds);

//            return Arrays.asList(responseEntity.getBody());
            return recommendedRecruitments;
        }

//        return new ArrayList<>();

    }

}
