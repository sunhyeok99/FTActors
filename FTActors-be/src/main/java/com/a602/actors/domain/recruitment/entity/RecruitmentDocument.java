package com.a602.actors.domain.recruitment.entity;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;

@Document(indexName = "recruitment_elasticsearch") //해당 클래스의 인스턴스가 저장될 인덱스의 이름을 지정합니다.
//@Setting(settingPath = "es-config/es-analyzer.json")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecruitmentDocument { //엘라스틱 서치 디비 내부에 저장할 거
    @Id
    @Field(type = FieldType.Long)
    private Long id; //공고 게시글 번호

//    @Field(type = FieldType.Text, analyzer = "korean_analyzer", searchAnalyzer = "korean_analyzer")
    @Field(type = FieldType.Text) // 형태소 분석할 텍스트 (검색에 활용)
    private String title; //제목 (노리를 활용한 형태소 분석도 함 -> 변경예정)

//    @Field(type = FieldType.Text, analyzer = "korean_analyzer", searchAnalyzer = "korean_analyzer")
    @Field(type = FieldType.Text) // 형태소 분석할 텍스트 (검색에 활용)
    private String content; //내용 (노리를 활용한 형태소 분석도 함 -> 변경예정)

    @Field(type = FieldType.Keyword) //일반 텍스트 (형태소 분석 적용x, 검색 시 활용x)
    private String name; //실명

//    @Field(type = FieldType.Keyword) //일반 텍스트 (형태소 분석 적용x, 검색 시 활용x)
//    private String stageName; //예명? 아이디? 둘 중 뭐로 하지

    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second)
    private LocalDateTime createdTime; // 게시물 생성 일시

//    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second)
//    private LocalDateTime createdTime; // 게시물 생성 일시
//
////    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second)
//    @Field(type = FieldType.Keyword)
//    private LocalDateTime updatedTime; //마지막 업데이트 일시
//
////    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second)
//    @Field(type = FieldType.Keyword)
//    private String startDate; // 공고 시작 일시
//
//    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second)
    @Field(type = FieldType.Keyword)
    private String startDate; // 공고 시작 일시

    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second)
    private String endDate; // 공고 끝나는 일시

    @Field(type = FieldType.Keyword)
    private String privateRecruitment; //비공개 여부

    public static RecruitmentDocument from (Recruitment recruitment) {
        return RecruitmentDocument.builder()
                .id(recruitment.getId())
                .title(recruitment.getTitle())
                .content(recruitment.getContent())
                .name(recruitment.getMember().getName()) //아이디? 예명??
//                .stageName(recruitment.getMember().getStageName())
                .createdTime(recruitment.getCreatedAt())
//                .updatedTime(recruitment.getUpdatedAt())
                .startDate(recruitment.getStartDate())
                .endDate(recruitment.getEndDate())
                .privateRecruitment(recruitment.getPrivateRecruitment())
                .build();
    }

}
