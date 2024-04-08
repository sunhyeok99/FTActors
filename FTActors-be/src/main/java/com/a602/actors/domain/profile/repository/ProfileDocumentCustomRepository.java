package com.a602.actors.domain.profile.repository;

import com.a602.actors.domain.profile.entity.ProfileDocument;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.SearchHits;

import java.util.List;

public interface ProfileDocumentCustomRepository {

    List<ProfileDocument> findAllByOrderByUpdatedTime(int sorting);

    SearchHits<ProfileDocument> search(NativeQuery nativeQuery);

//    List<ProfileDocument> findByContent(String content);

}
