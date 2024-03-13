package com.a602.actors.domain.profile.service;

import com.a602.actors.domain.profile.dto.ProfileListDto;
import com.a602.actors.domain.profile.dto.ProfileRequest;
import com.a602.actors.domain.profile.entity.Profile;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public interface ProfileService {
    List<ProfileListDto> getProfileList(int sorting, Character condition, HttpSession session);

    ProfileListDto getProfile(Long memberId, Character condition);

    String canRemoveProfile(Long profileId, HttpSession session);

    String createProfile(ProfileRequest profileRequest, HttpSession session);

    String updateProfile(Long profileId, ProfileRequest profileRequest, HttpSession session);
}
