package com.a602.actors.domain.profile.service;

import com.a602.actors.domain.profile.dto.ProfileDto;
import com.a602.actors.domain.profile.dto.ProfileRequest;
import com.a602.actors.domain.profile.dto.ProfileSearchRequest;
import com.a602.actors.domain.profile.dto.ProfileSearchResponse;
import com.a602.actors.domain.profile.entity.ProfileDocument;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public interface ProfileService {
    List<ProfileSearchResponse> searchAllProfile(int sorting);

    ProfileDto getProfile(Long profileId, HttpSession session);
    List<ProfileSearchResponse> search(ProfileSearchRequest profileSearchRequest);

    ///------------------------read

    String deleteProfile(Long profileId);

    String createProfile(ProfileRequest profileRequest);

    String updateProfile(Long profileId, ProfileRequest profileRequest);

    List<ProfileSearchResponse> searchProfileByContent(List<String> keywordArr);
    List<ProfileSearchResponse> searchProfileByName(String keyword);

    List<ProfileSearchResponse> searchAllProfile22(int sorting, HttpServletRequest request);
}
