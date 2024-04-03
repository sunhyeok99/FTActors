package com.a602.actors.domain.profile.entity;

import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

import java.time.LocalDateTime;

//@EqualsAndHashCode(of = "id") // equals() 및 hashCode() 메서드를 생성할 때 고려해야 할 필드를 지정
@Document(indexName = "profile_elasticsearch") //해당 클래스의 인스턴스가 저장될 인덱스의 이름을 지정합니다. 이름 없으면
@Setting(settingPath = "es-config/nori_redvelvet.json")
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//@ToString
@Getter
@Setter
@AllArgsConstructor
@ToString
@Builder
public class ProfileDocument { //엘라스틱 서치 디비 내부에 저장할 거
    @Id
    @Field(type = FieldType.Keyword)
    private Long id; //게시글 번호

//    @Field(name = "member_id", type = FieldType.Keyword)
//    private Long memberId; //

    @Field(type = FieldType.Text, analyzer = "name_analyzer", searchAnalyzer = "name_analyzer") // -> (초성 검색 허용), 오타 허용
    private String name;

    //제목, 예명 TO do: Member에서 수정이 일어나면, profile에서도 똑같이 수정이 일어나야 한다. elasticsearch를 쓰게 되면서 문제가 생겼따
    @Field(name = "stage_name", type = FieldType.Text, analyzer = "name_analyzer", searchAnalyzer = "name_analyzer") // -> (초성 검색 허용), 오타 허용
    private String stageName;

    //    @Field(type = FieldType.Text, analyzer = "korean_analyzer", searchAnalyzer = "korean_analyzer")
    @Field(type = FieldType.Text, analyzer = "content_and_title_analyzer", searchAnalyzer = "content_and_title_analyzer")
    // -> 오타 허용, 자동 완성 (검색어 우선순위 적용)
//@Field(type = FieldType.Text,
//        analyzer = "content_and_title_analyzer",
//        searchAnalyzer = "content_and_title_analyzer")
//@MultiField(mainField = @Field(type = FieldType.Text),
//        otherFields = {@InnerField(suffix = "keyword", type = FieldType.Keyword)})
    private String content; //내용(자기소개)

    @Field(type = FieldType.Keyword)
    private String birth; //생년월일 add (생년만 잘라서 저장)

    @Field(type = FieldType.Keyword)
    private String gender; //성별 add

    @Field(type = FieldType.Keyword) //일반 텍스트 (형태소 분석 적용x)
    private Character type; //배우 프로필? 관계자 프로필?

    @Field(name = "private_post", type = FieldType.Keyword) //일반 텍스트 (형태소 분석 적용x)
    private Character privatePost;

    @Field(name = "created_at", type = FieldType.Keyword)
//    @Field(name = "created_at", type = FieldType.Date, format = DateFormat.date_hour_minute_second)
    private LocalDateTime createdTime; //생성 일시

    //    @Field(name = "updated_at", type = FieldType.Date, format = DateFormat.date_hour_minute_second)
    @Field(name = "updated_at", type = FieldType.Keyword)
    private LocalDateTime updatedTime; //마지막 업데이트 일시

    @Field(name = "image", type = FieldType.Keyword)
    private String image;
    @Field(name = "image_name", type = FieldType.Keyword)
    private String imageName;

    public static ProfileDocument from (Profile profile) {
        return ProfileDocument.builder()
                .id(profile.getId())
                .stageName(profile.getMember().getStageName())
                .content(profile.getContent())
                .type(profile.getType())
                .privatePost(profile.getPrivatePost())
                .createdTime(profile.getCreatedAt())
                .updatedTime(profile.getUpdatedAt())
                .gender(profile.getMember().getGender())
                .birth(extractBirthYear(profile.getMember().getBirth())) //생년만 저장
//                .imageLink(profile.getImage())
                .image(profile.getImage())
                .imageName(profile.getImageName())
                .build();
    }

    // 수정된 내용을 반영하여 객체를 업데이트하는 메서드
    public void updateContent(String newContent, Character newPrivatePost) {
        this.content = newContent;
        this.privatePost = newPrivatePost;
        this.updatedTime = LocalDateTime.now(); // 수정 시간을 현재 시간으로 갱신
    }

    // 생년 정보를 추출하는 메서드
    private static String extractBirthYear(String fullBirth) {
        // "-"를 기준으로 문자열을 분리하여 생년 정보를 추출
        String[] parts = fullBirth.split("-");
        if (parts.length > 0) {
            return parts[0]; // 첫 번째 요소가 생년 정보
        } else {
            return null; // 유효한 생년 정보가 없는 경우
        }
    }
}