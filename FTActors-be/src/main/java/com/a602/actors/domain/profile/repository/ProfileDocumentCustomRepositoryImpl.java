package com.a602.actors.domain.profile.repository;

import com.a602.actors.domain.profile.dto.ProfileRequest;
import com.a602.actors.domain.profile.entity.ProfileDocument;
import com.a602.actors.global.elasticsearch.TimeChanger;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ProfileDocumentCustomRepositoryImpl implements ProfileDocumentCustomRepository {

    private final ElasticsearchOperations elasticsearchOperations;
    @Override
    public void updateProfileByProfileId(Long profileId, ProfileRequest profileRequest) {
        // 기존 문서를 가져옵니다.
        ProfileDocument existingDocument = elasticsearchOperations.get(String.valueOf(profileId), ProfileDocument.class);
        if (existingDocument != null) {
            // 새로운 내용으로 기존 문서를 업데이트합니다.
            existingDocument.setContent(profileRequest.getContent());
            existingDocument.setPrivatePost(profileRequest.getPrivateProfile());
            existingDocument.setUpdatedTime(TimeChanger.convertUtcToKoreaTime());

            // Elasticsearch에 새로운 내용으로 문서를 업데이트합니다.
            elasticsearchOperations.save(existingDocument);
        } else {
            // 해당 ID에 해당하는 문서가 없을 경우 처리할 작업을 수행합니다.
        }
    }
}
