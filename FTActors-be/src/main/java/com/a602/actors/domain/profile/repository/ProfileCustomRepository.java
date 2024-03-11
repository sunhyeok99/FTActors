package com.a602.actors.domain.profile.repository;

import com.a602.actors.domain.profile.entity.Profile;

import java.util.List;

public interface ProfileCustomRepository {
    List<Profile> findAllLatest(int sorting, Character condition);

    Profile findProfileByIdAndCondition(Long memberId, Character condition);

    Profile findProfileById(Long profileId);
}
