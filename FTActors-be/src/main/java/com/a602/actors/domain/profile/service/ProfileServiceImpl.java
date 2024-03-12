package com.a602.actors.domain.profile.service;

import com.a602.actors.domain.member.Member;
import com.a602.actors.domain.profile.dto.ProfileRequest;
import com.a602.actors.domain.profile.entity.Profile;
import com.a602.actors.domain.profile.repository.ProfileCustomRepository;
import com.a602.actors.domain.profile.repository.ProfileRepository;
import com.a602.actors.domain.profile.repository.TmpMemRepoImpl;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ProfileServiceImpl implements ProfileService{
    private final ProfileRepository profileRepository;
    private final ProfileCustomRepository profileCustomRepository;
    private final TmpMemRepoImpl tmpMemRepo;

    @Override
    public List<Profile> getProfileList(int sorting, Character condition) {
        return profileCustomRepository.findAllLatest(sorting, condition);
    }

    @Override
    public Profile getProfile(Long memberId, Character condition) {
        return profileCustomRepository.findProfileByIdAndCondition(memberId, condition);
    }
    @Override
    public int createProfile(ProfileRequest profileRequest, HttpSession session) {
        String nowLoginId = (String) session.getAttribute("memberName");

//        //로그인이 안 되어 있으면x
//        if(nowLoginId == null) {
//            log.info("생성불가 - 로그인이 안 되었다!");
//            return 403;
//        }

        //타입(condition)과 member_id(멤버 고유번호)로 확인
        Character condition = profileRequest.getCondition();
        Member loginMember = tmpMemRepo.findByLoginId(nowLoginId); //멤버 쪽에서...
        Long loginMemberId = loginMember.getId();

        //이미 프로필이 생성되어 있으면x
        if( profileCustomRepository.existProfile(condition, loginMemberId) ) {
            log.info("생성불가 - 이미 있는 거면 불가!");
            return 409; //conflict 코드
        }

        //저장하기
        Profile creatingProfile = Profile.builder()
                .member(loginMember)
                .content(profileRequest.getSelfIntroduction())
                .type(profileRequest.getCondition())
                .portfolio(profileRequest.getPortfolioLink())
                .privatePost('F')
                .build();

        profileRepository.save(creatingProfile);
        System.out.println(creatingProfile);
        return 200;
    }

    @Override
    public int canRemoveProfile(Long profileId, HttpSession session) {
        Profile profile = profileCustomRepository.findProfileById(profileId);
//        System.out.println(profile.toString());
        String nowLoginId = (String) session.getAttribute("memberName");

        //프로필이 존재하지 않으면 x
        if(profile == null) return 404;
        //로그인이 안 되어 있으면x
//        if(nowLoginId == null) {
//            log.info("삭제불가 - 로그인이 안 되었다!");
//            return 403;
//        }
        //로그인 멤버와 지금 멤버가 다르면x (추후 구현)
        if( !profile.getMember().getMemberId().equals(nowLoginId) ) {
            log.info("삭제불가 - 다른 사람이다!!");
            return 403;
        }

        //성공하면 프로필 삭제 후 200리턴
        profileRepository.deleteById(profileId);
        return 200;
    }
    @Override
    public int updateProfile(Long profileId, ProfileRequest profileRequest, HttpSession session) {
        String nowLoginId = (String) session.getAttribute("memberName");
        Profile profile = profileCustomRepository.findProfileById(profileId);
        Member loginMember = tmpMemRepo.findByLoginId(nowLoginId); //멤버 쪽에서...

        //로그인이 안 되어 있으면x
        if(nowLoginId == null) {
            log.info("수정불가 - 로그인이 안 되었다!");
            return 403;
        }

        //프로필이 존재하지 않으면 x
        if(profile == null) {
            log.info("수정불가 - 존재하지 않는 프로필!");
            return 404;
        }
        //본인 게 아니면x
        if(!profile.getMember().getMemberId().equals(nowLoginId)) {
            log.info("수정불가 - 너 거 아니잖아!");
            return 403;
        }

        //수정 성공
        profileCustomRepository.updateProfile(profileId, profileRequest);
        return 200;
    }

}
