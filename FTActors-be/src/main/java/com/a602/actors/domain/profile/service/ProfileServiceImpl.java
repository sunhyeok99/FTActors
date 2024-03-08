package com.a602.actors.domain.profile.service;

import com.a602.actors.domain.profile.dto.Profile;
import com.a602.actors.domain.profile.repository.ProfileCustomRepository;
import com.a602.actors.domain.profile.repository.ProfileRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService{
    private final ProfileRepository profileRepository;
    private final ProfileCustomRepository profileCustomRepository;

    @Override
    public List<Profile> getProfileList() {
        return profileRepository.findAll();
    }

    @Override
    public Profile getProfile(Long memberId) {
        return profileCustomRepository.findById(memberId);
    }
}
