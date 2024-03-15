package com.a602.actors.domain.profile.entity;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.*;

import java.time.LocalDateTime;

@Document(indexName = "indexnamewhat") //해당 클래스의 인스턴스가 저장될 인덱스의 이름을 지정합니다. 데이터베이스 내의 "artscope-test-post2"라는 이름의 인덱스에 저장. 이름 없으면 만듦
//@Setting(settingPath = "es-config/es-analyzer.json")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileDocument { //엘라스틱 서치 디비 내부에 저장할 거...
    @Id
    @Field(type = FieldType.Long)
    private Long id; //게시글 번호??? 아닌가?? 뭐지??

//    @Field(type = FieldType.Text, analyzer = "korean_analyzer", searchAnalyzer = "korean_analyzer")
    @Field(type = FieldType.Text)
    private String memberId; //제목 (노리를 활용한 형태소 분석도 함 -> 변경예정)

//    @Field(type = FieldType.Text, analyzer = "korean_analyzer", searchAnalyzer = "korean_analyzer")
    @Field(type = FieldType.Text)
    private String content; //내용 (노리를 활용한 형태소 분석도 함 -> 변경예정)

    @Field(type = FieldType.Text)
    private Character privatePost;

    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second)
    private LocalDateTime createdTime; //생성 일시

    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second)
    private LocalDateTime updatedTime; //마지막 업데이트 일시

    public static ProfileDocument from (Profile profile) {
        return ProfileDocument.builder()
                .id(profile.getId())
                .memberId(profile.getMember().getMemberId()) //나중에 stage_name으로 바꾸기
                .content(profile.getContent())
                .privatePost(profile.getPrivatePost())
                .createdTime(profile.getCreatedAt())
                .updatedTime(profile.getUpdatedAt())
                .build();
    }

}
