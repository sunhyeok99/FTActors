package com.a602.actors.domain.recruitment.crawling;

import com.a602.actors.domain.recruitment.entity.Recruitment;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Crawling {

    public List<Recruitment> getRecruitmentDatas() {
        final String RECRUITMENT_URL = "https://www.filmmakers.co.kr/filmmakersWanted/category/";
        int[] urlCode = {63707, 63711, 8295195, 2361256, 63816}; // 장편영화 / 단편영화 / 웹드라마 / 뮤비/CF / 유튜브/기타
        List<Recruitment> recruitmentList = new ArrayList<>();
        try {
            for (int index = 0; index < urlCode.length; index++) {
                Document document = Jsoup.connect(RECRUITMENT_URL + urlCode[index]).get(); // 주소뒤에 100, 101 이런식
                Elements content1 = document.select(".block"); // 날짜 불러옴
                Elements content2 = document.select(".sh_text_headline"); // 주소 불러옴


                for (int i = 0; i < content1.size(); i++) {
//                    String title = content1.get(i).text(); // 제목은 text만 추출
//                    String description = content2.get(i).text(); // 설명도 text만 추출
//                    String url = content1.get(i).absUrl("href"); // url은 제목의 a태그 추출
//                    String image = content3.get(i).absUrl("src"); // image 사진 링크 추출
                System.out.println(content1.get(i).text());
                System.out.println(content2.get(i).absUrl("href"));

//                   Recruitment recruitment = Recruitment.builder()
//
//                           .build();
//                    recruitmentList.add(recruitment);
                }
            }
        } catch (IOException e) {
            // 크롤링 중에 예외가 발생할 경우
            e.printStackTrace(); // 예외를 출력하거나 로깅하도록 수정해도 좋습니다.
        }
        return recruitmentList;
    }

}
