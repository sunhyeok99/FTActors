<<<<<<< HEAD
package com.a602.actors.domain.profile.repository;

import co.elastic.clients.elasticsearch._types.query_dsl.*;
import com.a602.actors.domain.profile.entity.ProfileDocument;
import com.a602.actors.global.elasticsearch.service.query.queryBuilder.CustomElasticsearchOperations;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.client.elc.NativeQueryBuilder;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository
@Component
@RequiredArgsConstructor
public class ProfileDocumentCustomRepositoryImpl implements ProfileDocumentCustomRepository {

    private final ElasticsearchOperations elasticsearchOperations;
    private final CustomElasticsearchOperations customElasticsearchOperations;

    @Override
    public List<ProfileDocument> findAllByOrderByUpdatedTime(int sorting) {
        NativeQueryBuilder queryBuilder = new NativeQueryBuilder();
        TermQuery.Builder termQueryBuilder = QueryBuilders.term()
                .field("private_post")
                .value("F");

        NativeQuery nativeQuery = queryBuilder.withQuery(q -> q
                        .term(termQueryBuilder.build()))
                .build();
        System.out.println("네이티브 쿼리ㅣ: "+ nativeQuery.getQuery().toString());

        Sort sort;
        if (sorting == 1) { // 최신
            sort = Sort.by(Sort.Direction.DESC, "updatedTime");
        } else { //오래된 순
            sort = Sort.by(Sort.Direction.ASC, "updatedTime");
        }

        // 정렬 조건을 사용하여 프로필 문서 조회
//        Iterable<ProfileDocument> profileDocuments = profileDocumentRepository.findAll(sort);
        // 정렬 조건과 쿼리를 사용하여 프로필 문서 조회
        List<ProfileDocument> profileDocuments = customElasticsearchOperations.queryForList(nativeQuery, sort);
        System.out.println("리포지토리 프로필뱉으라독: "+ profileDocuments);

        // Iterable을 List로 변환하여 반환
        return StreamSupport.stream(profileDocuments.spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public SearchHits<ProfileDocument> search(NativeQuery nativeQuery) {
        return elasticsearchOperations.search(nativeQuery, ProfileDocument.class);
    }

//    @Override
//    public List<ProfileDocument> findAllByOrderByUpdatedTimeAsc(Sort sort) {
//        return null;
//    }
}
=======
package com.a602.actors.domain.profile.repository;

import co.elastic.clients.elasticsearch._types.query_dsl.*;
import com.a602.actors.domain.profile.entity.ProfileDocument;
import com.a602.actors.global.elasticsearch.service.query.queryBuilder.CustomElasticsearchOperations;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.client.elc.NativeQueryBuilder;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository
@Component
@RequiredArgsConstructor
public class ProfileDocumentCustomRepositoryImpl implements ProfileDocumentCustomRepository {

    private final ElasticsearchOperations elasticsearchOperations;
    private final CustomElasticsearchOperations customElasticsearchOperations;

    @Override
    public List<ProfileDocument> findAllByOrderByUpdatedTime(int sorting) {
        NativeQueryBuilder queryBuilder = new NativeQueryBuilder();
        TermQuery.Builder termQueryBuilder = QueryBuilders.term()
                .field("private_post")
                .value("F");

        NativeQuery nativeQuery = queryBuilder.withQuery(q -> q
                        .term(termQueryBuilder.build()))
                .build();
//        System.out.println(nativeQuery);

        Sort sort;
        if (sorting == 1) { // 최신
            sort = Sort.by(Sort.Direction.DESC, "updatedTime");
        } else { //오래된 순
            sort = Sort.by(Sort.Direction.ASC, "updatedTime");
        }

        // 정렬 조건을 사용하여 프로필 문서 조회
//        Iterable<ProfileDocument> profileDocuments = profileDocumentRepository.findAll(sort);
        // 정렬 조건과 쿼리를 사용하여 프로필 문서 조회
        List<ProfileDocument> profileDocuments = customElasticsearchOperations.queryForList(nativeQuery, sort);

        // Iterable을 List로 변환하여 반환
        return StreamSupport.stream(profileDocuments.spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public SearchHits<ProfileDocument> search(NativeQuery nativeQuery) {
        return elasticsearchOperations.search(nativeQuery, ProfileDocument.class);
    }

//    @Override
//    public List<ProfileDocument> findAllByOrderByUpdatedTimeAsc(Sort sort) {
//        return null;
//    }
}
>>>>>>> develop-be
