package com.a602.actors.domain.profile.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileSearchRequest { //이름 검색, 예명 검색, 생년월일 검색, 성별 검색, 내용(자기소개) 검색
//    private Integer page = 1;
//    private Integer size = 10;

    private String query; //검색할 키워드
}
