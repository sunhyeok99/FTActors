package com.a602.actors.domain.recommend.controller;


import com.a602.actors.domain.recommend.service.RecommendService;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// 모델 controller
@RestController
@RequestMapping("/recommend")
public class RecommendController {

    final RecommendService recommendService;

    public RecommendController(RecommendService recommendService){
        this.recommendService = recommendService;
    }

    @GetMapping("/list")
    public String getList(){

        return recommendService.test();
    }

    @PostMapping("/recruitment")
    public List<?> getRecommendRecruitment(Long memberId) {
        return recommendService.getRecommendedRecruitment(memberId);
    }
}
