//package com.a602.actors.domain.profile.service;
//
//import com.a602.actors.domain.profile.dto.ProfileDto;
//import com.a602.actors.domain.profile.dto.ProfileRequest;
//import com.a602.actors.domain.profile.entity.ProfileDocument;
//import jakarta.servlet.http.HttpSession;
//
//import java.util.List;
//
//public interface ProfileService {
//    List<ProfileDto> getProfileList(int sorting, Character condition, HttpSession session);
//
//    ProfileDto getProfile(Long profileId, HttpSession session);
//
//    String canRemoveProfile(Long profileId, HttpSession session);
//
//    String createProfile(ProfileRequest profileRequest, HttpSession session);
//
//    String updateProfile(Long profileId, ProfileRequest profileRequest, HttpSession session);
//
//    List<ProfileDocument> searchProfileDocuments(List<String> keywordArr);
//}
