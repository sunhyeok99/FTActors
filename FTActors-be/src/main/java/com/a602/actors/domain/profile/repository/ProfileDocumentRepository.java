package com.a602.actors.domain.profile.repository;

import com.a602.actors.domain.profile.entity.ProfileDocument;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileDocumentRepository extends ElasticsearchRepository<ProfileDocument, Long> {
    @Query("{\"bool\": {\"must_not\": {\"term\": {\"privatePost\": \"T\"}}}}")
    List<ProfileDocument> findAllByOrderByUpdatedTimeDesc();
    @Query("{\"bool\": {\"must_not\": {\"term\": {\"privatePost\": \"T\"}}}}")
    List<ProfileDocument> findAllByOrderByUpdatedTimeAsc();

    List<ProfileDocument> findByContent(String content);
}
