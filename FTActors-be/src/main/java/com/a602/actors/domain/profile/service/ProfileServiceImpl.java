package com.a602.actors.domain.profile.service;

import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders;
import com.a602.actors.domain.member.Member;
import com.a602.actors.domain.profile.dto.ProfileDto;
import com.a602.actors.domain.profile.dto.ProfileRequest;
import com.a602.actors.domain.profile.dto.ProfileSearchRequest;
import com.a602.actors.domain.profile.dto.ProfileSearchResponse;
import com.a602.actors.domain.profile.entity.Profile;
import com.a602.actors.domain.profile.entity.ProfileDocument;
import com.a602.actors.domain.profile.mapper.ProfileMapper;
import com.a602.actors.domain.profile.repository.ProfileCustomRepository;
import com.a602.actors.domain.profile.repository.ProfileDocumentRepository;
import com.a602.actors.domain.profile.repository.ProfileRepository;
import com.a602.actors.domain.profile.repository.TmpMemRepoImpl;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.*;
import org.springframework.stereotype.Service;

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
    private final ElasticsearchOperations elasticsearchOperations;
//    private final LogstashService logstashService; //로그스태시 호출 전용

    @Override //삭제할 메서드 (이거 기반으로 searchAllProfile 구현 마무리하고 삭제)
    public List<ProfileDocument> getProfileList(int sorting, Character condition, HttpSession session) {
        log.info("실환가요 - 엘라스틱 서치- 목록 다 뽑기");
        
        return null;
//        String nowLoginId = (String) session.getAttribute("memberName");
//        List<Profile> profiles = null;
//        Long loginnedId = (long) -1;
//
//        //로그인 여부 확인 후, 로그인이 되어 있으면
//        if(nowLoginId != null) {
//            Member loginMember = tmpMemRepo.findByLoginId(nowLoginId);
//            loginnedId = loginMember.getId();
//        }
//
//        profiles = profileCustomRepository.findAllLatest(sorting, condition, loginnedId);
//
//        return profileMapper.ProfileListToProfileDtoList(profiles);
//        return profileCustomRepository.findAllLatest(sorting, condition);
    }

    @Override //검색 목록 (엘라스틱만 , db사용x)
    public List<ProfileSearchResponse> searchAllProfile(int sorting) {
//        Sort sort = Sort.by(Sort.Direction.DESC, "updatedTime"); //1이면 최신순
//        if (sorting == 2) sort = Sort.by(Sort.Direction.ASC, "updatedTime"); //2이면 오래된 순
//
//        Iterable<ProfileDocument> iterable = profileDocumentRepository.findAll(sort);
//        for (ProfileDocument document : iterable) {
//            System.out.println(document);
//        }
//
//        return StreamSupport.stream(iterable.spliterator(), false)
//                .map(this::convertToSearchResponse) //변환하는 중간 연산 (Document엔터티 -> 응답 dto)
//                .collect(Collectors.toList()); //스트림을 리스트로 돌림

        //Reop가서 List<도큐먼트>로 뽑아오고
        List<ProfileDocument> list = null;
        if(sorting == 1) { //최신 순
            list = profileDocumentRepository.findAllByOrderByUpdatedTimeDesc(Sort.by(Sort.Direction.DESC, "updatedTime"));
        }
        else { //오래된 순
            list = profileDocumentRepository.findAllByOrderByUpdatedTimeAsc(Sort.by(Sort.Direction.ASC, "updatedTime"));
        }

        //리턴에서 mapper사용해서 변환 후 돌려주기
        return profileMapper.ProfileDocumentListToProfileSearchResponseList(list);
    }

    @Override //To do: session만 jwt로 바꾸기 (db만, 엘라스틱x)
    public ProfileDto getProfile(Long profileId, HttpSession session) {
        Long nowLoginId = (Long) session.getAttribute("memberName");
        Long loginnedId = (long) -1;

        //로그인 여부 확인 후, 로그인이 되어 있으면
        if(nowLoginId != null) {
            Member loginMember = tmpMemRepo.findByLoginId(nowLoginId);
            loginnedId = loginMember.getId();
        }

        Profile profile = profileCustomRepository.findProfileByIdAndCondition(profileId, loginnedId);

        //To do: 엔터티 -> Dto 돌릴 때, member객체에서 member id만 뽑아오는 거 구현체!!!!

        return profileMapper.ProfileToProfileDto(profile);
    }

    @Override //개어려어 -> searchAllProfile에 통합하기 (엘라스틱만 , db사용x)
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
    @Override //프로필 만들기 / 개얼여 (엘라스틱, db 둘 다 사용)
    public String createProfile(ProfileRequest profileRequest) {

        //이미 프로필이 생성되어 있으면x
        if( profileCustomRepository.existProfile(profileRequest.getType(), profileRequest.getMemberId()) ) {
            log.info("생성불가 - 이미 있는 거면 불가!");
//            return "";
        }

        //jwt 구현 후 삭제
//        Member loginMember = new Member(
//                "daniel",
//                "다니엘",
//                "daniel@naver.com",
//                null,
//                null,
//                "010-8888-8888",
//                "951111",
//                "F",
//                null,
//                profileRequest.getStageName()
//        );
        Member loginMember = tmpMemRepo.findByLoginId(7L);
        //-------jwt 구현 후 삭제

        //저장하기
        Profile creatingProfile = Profile.builder()
                .member(loginMember)
                .content(profileRequest.getContent())
                .type(profileRequest.getType())
                .portfolio(profileRequest.getPortfolioLink())
                .privatePost('F') //처음 생성할 땐 무조건 공개
                .build();

        profileRepository.save(creatingProfile); //왜 두 개씩 만들어지니? -> 엘라스틱서치 repo에서 db도 만든다;;; 왕...
        profileDocumentRepository.save(ProfileDocument.from(creatingProfile)); //안 되면 써야지....
        return "";
    }

    @Override // (엘라스틱, db 둘 다 사용)
    public String deleteProfile(Long profileId) {
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(IllegalArgumentException::new);

        //로그인 멤버와 지금 멤버가 다르면x 
//        if( !profile.getMember().getMemberId().equals(nowLoginId) ) {
//            log.info("삭제불가 - 다른 사람이다!!");
//        }
        //DB에 관련된 사진 S3에서 지우기
//        if(!barter.getImages().get(0).getImgCode().equals("icon.PNG"))
//            deleteS3(barter.getImages());

        //엘라스틱 서치 삭제하기 - 로그스태시
//        profileDocumentRepository.deleteById(profileId); //엘라스틱에서 먼저 없애고 dks/ 안 되면 써야지....
        profileRepository.deleteById(profileId); //DB에서 삭제
//        logstashService.sendDeleteRequest(profileId); //logstash에 없앨 거 생겼단다~! 알리기 (여기서 알리면 엘라스틱서치에 영향 줌)
        return "";
    }

//    private void publishDeleteEvent(Profile profile, int type) {
//        ApplicationEventPublisher publisher = ApplicationEventPublisherHolder.getPublisher();
//
//        if (publisher != null) {
//            LocalDateTime localDateTime = profile.getCreatedAt(); //혹은 updatedAt일 수도
//            Instant instant = localDateTime.toInstant(); //LocalDateTime 객체를 Instant 객체로 변환, Instant 객체가 현재 시간을 나타내는 것이 됨
//            ProfileDeleteEvent event = new ProfileDeleteEvent(new ProfileMessage(
//                    profile.getId(),
//                    profile.getType(),
//                    type,
//                    instant
//            ));
//
//        }
//    }

    @Override // (엘라스틱, db 둘 다 사용)
    public String updateProfile(Long profileId, ProfileRequest profileRequest, HttpSession session) {
        Long nowLoginId = (Long) session.getAttribute("memberName");
        Profile profile = profileCustomRepository.findProfileById(profileId);
        Member loginMember = tmpMemRepo.findByLoginId(nowLoginId); //멤버 쪽에서...

        //로그인이 안 되어 있으면x
        if(nowLoginId == null) {
            log.info("수정불가 - 로그인이 안 되었다!");
//            return 403;
        }

        //프로필이 존재하지 않으면 x
        if(profile == null) {
            log.info("수정불가 - 존재하지 않는 프로필!");
//            return 404;
        }
        //본인 게 아니면x
        if(!profile.getMember().getId().equals(nowLoginId)) {
            log.info("수정불가 - 너 거 아니잖아!");
//            return 403;
        }

        //수정 성공
        profileCustomRepository.updateProfile(profileId, profileRequest);
        //엘라스틱 서치 업데이트 - 로그스태시
        return "";
    }

    @Override // 다중 검색 용으로 만든 거 같은데 더 연구해야 할 듯 (엘라스틱만)
    public List<ProfileDocument> searchProfileDocuments(List<String> keywordArr) { //다중 검색?? 어케 하니
        String keyword = keywordArr.get(0);
        Query query = QueryBuilders.match(queryBuilder -> queryBuilder.field("content").query(keyword));
        NativeQuery nativeQuery = NativeQuery.builder().withQuery(query).build(); //쿼리가 너무 복잡해질 때를 대비해서 네이티브 쿼리로 한 번 돌려서 사용
        SearchHits<ProfileDocument> result = elasticsearchOperations.search(nativeQuery, ProfileDocument.class);

        return result
                .stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());
    }

}
