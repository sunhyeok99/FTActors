package com.a602.actors.domain.profile.service;

import com.a602.actors.domain.profile.entity.Profile;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public interface ProfileService {
    List<Profile> getProfileList(int sorting, Character condition);

    Profile getProfile(Long memberId, Character condition);

    int canRemoveProfile(Long profileId, HttpSession session);
}
