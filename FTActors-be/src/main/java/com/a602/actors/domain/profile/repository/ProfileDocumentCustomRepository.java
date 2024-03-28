package com.a602.actors.domain.profile.repository;

import com.a602.actors.domain.profile.entity.ProfileDocument;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.annotations.Query;

import java.util.List;

public interface ProfileDocumentCustomRepository {
//    @Query("{\"bool\": {\"must_not\": {\"term\": {\"privatePost\": \"T\"}}}}")
//    List<ProfileDocument> findAllByOrderByUpdatedTimeDesc(Sort sort);

    List<ProfileDocument> findAllByOrderByUpdatedTime(int sorting);
//    @Query("{\"bool\": {\"must_not\": {\"term\": {\"privatePost\": \"T\"}}}}")
//    List<ProfileDocument> findAllByOrderByUpdatedTimeAsc(Sort sort);

//    List<ProfileDocument> findByContent(String content);
}
