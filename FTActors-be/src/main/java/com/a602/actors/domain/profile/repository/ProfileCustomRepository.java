package com.a602.actors.domain.profile.repository;

import com.a602.actors.domain.profile.entity.Profile;

import java.util.List;

public interface ProfileCustomRepository {
    Profile findById(Long memberId);

    List<Profile> findAllLatest(int sorting);
}
