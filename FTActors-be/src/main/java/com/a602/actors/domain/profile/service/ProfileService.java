package com.a602.actors.domain.profile.service;

import com.a602.actors.domain.profile.dto.ProfileListDto;
import com.a602.actors.domain.profile.dto.ProfileRequest;
import com.a602.actors.domain.profile.entity.Profile;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public interface ProfileService {
    List<ProfileListDto> getProfileList(int sorting, Character condition);

    ProfileListDto getProfile(Long memberId, Character condition);

    int canRemoveProfile(Long profileId, HttpSession session);

    int createProfile(ProfileRequest profileRequest, HttpSession session);

    int updateProfile(Long profileId, ProfileRequest profileRequest, HttpSession session);
}
