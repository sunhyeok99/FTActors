package com.a602.actors.domain.profile.service;

import com.a602.actors.domain.profile.dto.Profile;

import java.util.List;

public interface ProfileService {
    List<Profile> getProfileList();

    Profile getProfile(Long memberId);
}
