package com.a602.actors.domain.profile.service;

import co.elastic.clients.elasticsearch._types.query_dsl.*;
import com.a602.actors.domain.member.Member;
import com.a602.actors.domain.profile.dto.ProfileDto;
import com.a602.actors.domain.profile.dto.ProfileRequest;
import com.a602.actors.domain.profile.dto.ProfileSearchRequest;
import com.a602.actors.domain.profile.dto.ProfileSearchResponse;
import com.a602.actors.domain.profile.entity.Profile;
import com.a602.actors.domain.profile.entity.ProfileDocument;
import com.a602.actors.domain.profile.mapper.ProfileMapper;
import com.a602.actors.domain.profile.repository.*;
import com.a602.actors.global.elasticsearch.TimeChanger;
import com.a602.actors.global.elasticsearch.filter.ContentFilter;
import com.a602.actors.global.elasticsearch.filter.QueryBuilderInterface;
import com.a602.actors.global.exception.ExceptionCodeSet;
import com.a602.actors.global.exception.ProfileException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.client.elc.NativeQueryBuilder;
import org.springframework.data.elasticsearch.core.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ProfileServiceImpl implements ProfileService{
    private final ProfileRepository profileRepository;
    private final ProfileCustomRepository profileCustomRepository;
    private final TmpMemRepoImpl tmpMemRepo;
    private final ProfileMapper profileMapper;
    private final ProfileDocumentRepository profileDocumentRepository; //was 엘라스틱 용
    private final ProfileDocumentCustomRepository profileDocumentCustomRepository;
//    private final ElasticsearchOperations elasticsearchOperations;
    private final QueryBuilderInterface queryBuilderInterface;

    @Override //검색 목록 (엘라스틱만 , db사용x)
    public List<ProfileSearchResponse> searchAllProfile(int sorting) {
        //To do: 1. 지금은 비공개여부=T면 다 안 뽑음. jwt들어오면, 로그인 유저의 경우 T라도 같이 뽑아오게 바꾸기

        //Reop가서 List<도큐먼트>로 뽑아오고
        List<ProfileDocument> list = new ArrayList<>();
        list = profileDocumentCustomRepository.findAllByOrderByUpdatedTime(sorting);

        //리턴에서 mapper사용해서 변환 후 돌려주기
        if (list == null) new ProfileException(ExceptionCodeSet.PROFILE_NOT_FOUND); // -> 프론트에서 null이면 "조건에 맞는 프로필이 없습니다" 반환
        return profileMapper.ProfileDocumentListToProfileSearchResponseList(list);
    }

    @Override //To do: jwt에서 사람 정보 뽑아와서 그 계정으로 만들기 (db만, 엘라스틱x)
    public ProfileDto getProfile(Long profileId, HttpSession session) {
//        Long nowLoginId = (Long) session.getAttribute("memberName");
        Long loginnedId = (long) 10;

        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new ProfileException(ExceptionCodeSet.PROFILE_NOT_FOUND));
        return profileMapper.ProfileToProfileDto(profile);
    }

    @Override //개어려어 -> searchAllProfile에 통합하기 (엘라스틱만 , db사용x) //xxx
    public List<ProfileSearchResponse> search(ProfileSearchRequest profileSearchRequest) {
        //1. SearchCountMessage로 검색어의 검색 횟수 저장 -> 횟수 높을 수록 우선순위로 보여주기 (x)
        //2. 교환? 그냥 포카교환이라서 쓰인 거인듯 (x)
        //3. 검색어, 검색 조건을 컴퓨터가 알아들을 수 있도록 정제 작업 -> 페이지네이션 안 하면 x
            //특수 문자나 공백 제거
            //동의어 처리 (예: '자동차'와 '차량'을 동일하게 취급)
            //대소문자 통합
            //불용어 처리 (예: 'and', 'or'와 같은 의미 없는 단어 제거)
            //검색 필드 지정 (예: 제목, 내용, 작성자 등)
        //4.
//        queryBuilder.createQuery(criteria);
//        NativeQuery searchQuery = queryBuilder.getSearch();
//
//        /* Call repository method and Pagination */
//        SearchHits<BarterDocument> searchHits = barterSearchRepository.findByOptions(searchQuery);
//        SearchPage<BarterDocument> searchPage = SearchHitSupport.searchPageFor(
//                searchHits,
//                queryBuilder.getPageRequest()
//        );
//        log.info(">>> hits : "+searchPage.getSearchHits().getTotalHits());
//        log.info(searchPage.getContent().toString());

        return null;
    }

    //-------------
    @Override //프로필 만들기 -> jwt에서 정보 뽑아서 그 계정으로 만들기 (엘라스틱, db 둘 다 사용)
    public String createProfile(ProfileRequest profileRequest) {
        //To do: 1 jwt에서 로그인한 loginedMember 정보 뽑기
        //To do: 2 loginedMember에서 id(고유 번호) 뽑기
        //To do: 3 loginedMember의 id + profileRequest.getType() 기반으로 이미 있는 프로필인지 확인

        //이미 프로필이 생성되어 있으면x
//        if( profileCustomRepository.existProfile(profileRequest.getType(), profileRequest.getMemberId()) ) {
//            log.info("생성불가 - 이미 있는 거면 불가!");
//            throw new ProfileException(ExceptionCodeSet.PROFILE_ALREADY_EXIST);
//        }

        Member loginMember = tmpMemRepo.findByLoginId(20L);
        //-------jwt 구현 후 삭제

        //저장하기
        Profile creatingProfile = Profile.builder()
                .member(loginMember)
                .content(profileRequest.getContent())
                .type(profileRequest.getType())
                .portfolio(profileRequest.getPortfolioLink())
                .privatePost('F') //처음 생성할 땐 무조건 공개

                .build();

        profileRepository.save(creatingProfile);

//        아래는 WAS 기반 엘라스틱 서치 -> db에는 저장이 실패했는데, 엘라스틱서치에는 저장이 되는 말도 안 되는 경우가 생길 수 있어 좋지 않다.
//        ProfileDocument creatingProfileDocument = ProfileDocument.from(creatingProfile);
//        creatingProfileDocument.setCreatedTime(TimeChanger.convertUtcToKoreaTime());
//        creatingProfileDocument.setUpdatedTime(TimeChanger.convertUtcToKoreaTime());
//        profileDocumentRepository.save(creatingProfileDocument);
        return "";
    }

    @Override // (엘라스틱, db 둘 다 사용) //esOperation으로 was로 만듦..
    public String deleteProfile(Long profileId) {
        //To do: 1 jwt에서 로그인한 loginedMember 정보 뽑기
        //To do: 2 loginedMember에서 id(고유 번호) 뽑기
        //To do: 3 파라미터로 들어온 profileId로 updatingProfile 정보 뽑기
        //To do: 4 updatingProfile의 member.id 뽑기
        //To do: 5 2의 결과값과 4의 결과값이 같은지 확인해서 true라면 정보 삭제
        //To do: 5-1 false라면 로그인불일치 ===> 프론트에서 처리할 듯(버튼 생성 유무)

        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new ProfileException(ExceptionCodeSet.PROFILE_NOT_FOUND)); // "삭제 불가 - 없는 프로필입니다."

        //로그인 멤버와 지금 멤버가 다르면x
//        if( !profile.getMember().getMemberId().equals(nowLoginId) ) {
//            log.info("삭제불가 - 다른 사람이다!!");
//        }
        //DB에 관련된 사진 S3에서 지우기
//        if(!barter.getImages().get(0).getImgCode().equals("icon.PNG"))
//            deleteS3(barter.getImages());

        profileRepository.deleteById(profileId); //DB에서 삭제
        profileDocumentRepository.deleteById(profileId); //elasticsearch에서 삭제
        return "";
    }

    @Override // (엘라스틱, db 둘 다 사용) //To do: 어떠한 설정을 이유로, logstash는 현재 자기가 보고 있는 pk값보다 작은 애는 안 본다. 고로 update가 일어나도 어차피 자기가 보고 있는 애보다 작을 것이기 때문에, 이것을 해결해줘야 한다.
    public String updateProfile(Long profileId, ProfileRequest profileRequest) {
        //To do: 1 jwt에서 로그인한 loginedMember 정보 뽑기
        //To do: 2 loginedMember에서 id(고유 번호) 뽑기
        //To do: 3 파라미터로 들어온 profileId로 updatingProfile 정보 뽑기
        //To do: 4 updatingProfile의 member.id 뽑기
        //To do: 5 2의 결과값과 4의 결과값이 같은지 확인해서 true라면 정보 수정 -.프론트 처리
        //To do: 5-1 false라면 로그인불일치 ===> 프론트에서 처리할 듯(버튼 생성 유무)

        //profile찾기
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new ProfileException(ExceptionCodeSet.PROFILE_NOT_FOUND)); // "수정 불가 - 없는 프로필입니다."

        // db 수정 성공
        profileCustomRepository.updateProfile(profileId, profileRequest);
        //엘라스틱 서치 업데이트 -> WAS 기반이기 때문에 쓰지 말자. logstash를 어떻게 할지 찾자
//        profileDocumentCustomRepository.updateProfileByProfileId(profileId, profileRequest);
        return "";
    }

    @Override // 3. 여러 키워드 검색
    public List<ProfileSearchResponse> searchProfileByContent(List<String> keywords) {

        System.out.println("keywords = " + keywords);
        System.out.println("keywords.size() = " + keywords.size());
        System.out.println("keywords.isEmpty() = " + keywords.isEmpty());
//        if (!keywords.isEmpty()) {
//            return searchAllProfile(1);
//        }

        queryBuilderInterface.createQuery(keywords);
        NativeQuery nativeQuery = queryBuilderInterface.getSearch();
        System.out.println(nativeQuery.getQuery().toString());

        //
//        NativeQuery nativeQuery = NativeQuery.builder().withQuery(query).build(); //쿼리가 너무 복잡해질 때를 대비해서 네이티브 쿼리로 한 번 돌려서 사용
        SearchHits<ProfileDocument> searchHits = profileDocumentCustomRepository.search(nativeQuery);
        System.out.println(searchHits.getTotalHits());
        List<ProfileDocument> profileDocuments = new ArrayList<>();
        for (SearchHit<ProfileDocument> hit : searchHits) {
            profileDocuments.add(hit.getContent());
        }

        return profileMapper.ProfileDocumentListToProfileSearchResponseList(profileDocuments);
    }

    @Override
    public List<ProfileSearchResponse> searchProfileByName(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return searchAllProfile(1);
        }
        Query query = QueryBuilders.match(queryBuilder -> queryBuilder.field("stage_name").query(keyword));
        NativeQuery nativeQuery = NativeQuery.builder().withQuery(query).build(); //쿼리가 너무 복잡해질 때를 대비해서 네이티브 쿼리로 한 번 돌려서 사용
        SearchHits<ProfileDocument> result = profileDocumentCustomRepository.search(nativeQuery);

        List<ProfileDocument> profileDocuments = new ArrayList<>();
        for (SearchHit<ProfileDocument> hit : result) {
            profileDocuments.add(hit.getContent());
        }

        return profileMapper.ProfileDocumentListToProfileSearchResponseList(profileDocuments);
    }

}
