package com.a602.actors.global.elasticsearch.service.query.queryBuilder;

import com.a602.actors.domain.profile.entity.ProfileDocument;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;

import java.util.List;

public interface CustomElasticsearchOperations {
    List<ProfileDocument> queryForList(NativeQuery nativeQuery, Sort sort);
}
