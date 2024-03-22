package com.a602.actors.domain.profile.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileSearchRequest { //스테이지 네임 검색 or 내용 검색
//    private Integer page = 1;
//    private Integer size = 10;

    private String query; //검색할 키워드
}
