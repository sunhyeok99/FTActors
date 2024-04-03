package com.a602.actors.global.elasticsearch.service.query.queryBuilder;

import com.a602.actors.domain.profile.entity.ProfileDocument;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CustomElasticsearchTemplate implements CustomElasticsearchOperations {
    private final ElasticsearchTemplate elasticsearchTemplate;

    @Override
    public List<ProfileDocument> queryForList(NativeQuery nativeQuery, Sort sort) {

        SearchHits<ProfileDocument> searchHits = elasticsearchTemplate.search(nativeQuery, ProfileDocument.class);

        List<ProfileDocument> profileDocuments = new ArrayList<>();
        searchHits.forEach(hit -> profileDocuments.add(hit.getContent()));
        System.out.println("왜 안되죠? " + profileDocuments);
        
        return profileDocuments;
    }
}
