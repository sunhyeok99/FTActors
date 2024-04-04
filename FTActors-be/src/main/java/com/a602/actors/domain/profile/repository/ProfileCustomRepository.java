package com.a602.actors.domain.profile.repository;

import com.a602.actors.domain.profile.dto.Profile;

public interface ProfileCustomRepository {
    Profile findById(Long memberId);
}
