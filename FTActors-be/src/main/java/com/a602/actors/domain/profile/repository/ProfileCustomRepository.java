package com.a602.actors.domain.profile.repository;

import com.a602.actors.domain.profile.dto.ProfileRequest;
import com.a602.actors.domain.profile.entity.Profile;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

public interface ProfileCustomRepository {
//    Profile findProfileByIdAndCondition(Long profileId, Long loginnedId);
    Optional<Profile> findProfileByIdAndCondition(Long profileId, Long loginnedId);

    boolean existProfile(Character condition, Long loginMemberId);

    Profile findProfileById(Long profileId);

    void updateProfile(Long profileId, ProfileRequest profileRequest);
    Long existActorInMyPage(@RequestParam(name = "memberId") Long memberId);

    Long existPDInMyPage(@RequestParam(name = "memberId") Long memberId);
}
