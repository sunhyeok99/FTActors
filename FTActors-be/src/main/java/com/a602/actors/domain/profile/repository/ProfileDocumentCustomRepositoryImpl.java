package com.a602.actors.domain.profile.repository;

import co.elastic.clients.elasticsearch._types.query_dsl.*;
import com.a602.actors.domain.profile.entity.ProfileDocument;
//import com.a602.actors.global.elasticsearch.service.query.queryBuilder.QueryBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.client.elc.NativeQueryBuilder;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Component
@RequiredArgsConstructor
public class ProfileDocumentCustomRepositoryImpl implements ProfileDocumentCustomRepository {
    private final ElasticsearchOperations elasticsearchOperations;
//    private final NativeQueryBuilder nativeQueryBuilder;

    @Override
    public List<ProfileDocument> findAllByOrderByUpdatedTimeDesc(Sort sort) {
        NativeQueryBuilder queryBuilder = new NativeQueryBuilder();
//                new CriteriaQuery(criteria).addSort(sort);
        TermQuery.Builder termQueryBuilder = QueryBuilders.term()
                .field("private_post")
                .value("F");

        NativeQuery nativeQuery = queryBuilder.withQuery(q -> q
                .term(termQueryBuilder.build()))
                .build();
//        System.out.println(nativeQuery);
        SearchHits<ProfileDocument> searchHits = elasticsearchOperations.search(nativeQuery, ProfileDocument.class);
        System.out.println(searchHits.getTotalHits());
        List<ProfileDocument> profileDocuments = new ArrayList<>();
        searchHits.forEach(hit -> profileDocuments.add(hit.getContent()));

        return profileDocuments;
    }

//    @Override
//    public List<ProfileDocument> findAllByOrderByUpdatedTimeAsc(Sort sort) {
//        return null;
//    }
}
