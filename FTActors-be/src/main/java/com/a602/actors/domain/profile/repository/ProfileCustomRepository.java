package com.a602.actors.domain.profile.repository;

import com.a602.actors.domain.profile.dto.ProfileRequest;
import com.a602.actors.domain.profile.entity.Profile;

import java.util.List;

public interface ProfileCustomRepository {
    List<Profile> findAllLatest(int sorting, Character condition, Long memberId);

    Profile findProfileByIdAndCondition(Long profileId, Long loginnedId);

    boolean existProfile(Character condition, Long loginMemberId);

    Profile findProfileById(Long profileId);

    void updateProfile(Long profileId, ProfileRequest profileRequest);

}
