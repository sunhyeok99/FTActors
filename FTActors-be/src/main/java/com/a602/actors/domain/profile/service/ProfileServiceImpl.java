package com.a602.actors.domain.profile.service;

import com.a602.actors.domain.member.Member;
import com.a602.actors.domain.profile.entity.Profile;
import com.a602.actors.domain.profile.repository.ProfileCustomRepository;
import com.a602.actors.domain.profile.repository.ProfileRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ProfileServiceImpl implements ProfileService{
    private final ProfileRepository profileRepository;
    private final ProfileCustomRepository profileCustomRepository;

    @Override
    public List<Profile> getProfileList(int sorting, Character condition) {
        return profileCustomRepository.findAllLatest(sorting, condition);
    }

    @Override
    public Profile getProfile(Long memberId, Character condition) {
        return profileCustomRepository.findProfileByIdAndCondition(memberId, condition);
    }

    @Override
    public int canRemoveProfile(Long profileId, HttpSession session) {
        Profile profile = profileCustomRepository.findProfileById(profileId);
//        System.out.println(profile.toString());
        String tmpId = (String) session.getAttribute("memberName");

        //프로필이 존재하지 않으면 x
        if(profile == null) return 404;
        //로그인이 안 되어 있으면x (추후 구현)
        if(tmpId == null) {
            log.info("삭제불가 - 로그인이 안 되었다!");
            return 403;
        }
        //로그인 멤버와 지금 멤버가 다르면x (추후 구현)
        if( !profile.getMember().getMemberId().equals(tmpId) ) {
            log.info("삭제불가 - 다른 사람이다!!");
            return 403;
        }

        //성공하면 프로필 삭제 후 200리턴
        profileRepository.deleteById(profileId);
        return 200;
    }
}
