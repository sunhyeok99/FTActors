package com.a602.actors.domain.profile.repository;

import com.a602.actors.domain.profile.dto.ProfileRequest;

public interface ProfileDocumentCustomRepository {
    void updateProfileByProfileId(Long profileId, ProfileRequest profileRequest);
}
