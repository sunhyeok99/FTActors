<<<<<<< HEAD
package com.a602.actors.global.elasticsearch.filter;

import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.client.elc.NativeQueryBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QueryBuilder implements QueryBuilderInterface{
    private final NativeQueryBuilder nativeQueryBuilder;

    public QueryBuilder(){
        this.nativeQueryBuilder = new NativeQueryBuilder();
    }

    @Override
    public void createContentQuery(List<String> keywords) {
        this.setFilter(keywords);
    }

//    @Override
//    public void createNameQuery(String keyword) {
//        this.setMonoFilter(keyword);
//    }

//    private void setMonoFilter(String keyword) {
//        Query query = QueryBuilders.match(queryBuilder -> queryBuilder.field("stage_name").query(keyword));
////        NativeQuery nativeQuery = NativeQuery.builder().withQuery(query).build();
//        this.nativeQueryBuilder.withQuery(query);
//    }

    private void setFilter(List<String> keywords) {
        BoolQuery.Builder boolQueryBuilder = QueryBuilders.bool();
        for (String keyword : keywords) {
            if(keyword != null)
                boolQueryBuilder.should(ContentFilter.createFilter(keyword));
        }

        this.nativeQueryBuilder.withQuery(q -> q
                .bool(boolQueryBuilder.build())
        );
    }

    @Override
    public NativeQuery getSearch() {
        return this.nativeQueryBuilder.build();
    }
}
=======
package com.a602.actors.global.elasticsearch.filter;

import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.client.elc.NativeQueryBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QueryBuilder implements QueryBuilderInterface{
    private final NativeQueryBuilder nativeQueryBuilder;

    public QueryBuilder(){
        this.nativeQueryBuilder = new NativeQueryBuilder();
    }

    @Override
    public void createQuery(List<String> keywords) {
        this.setFilter(keywords);
    }

    @Override
    public void createQuery(String keyword) {
        this.setMonoFilter(keyword);
    }

    private void setMonoFilter(String keyword) {
        Query query = QueryBuilders.match(queryBuilder -> queryBuilder.field("stage_name").query(keyword));
//        NativeQuery nativeQuery = NativeQuery.builder().withQuery(query).build();
        this.nativeQueryBuilder.withQuery(query);
    }

    private void setFilter(List<String> keywords) {
        BoolQuery.Builder boolQueryBuilder = QueryBuilders.bool();
        for (String keyword : keywords) {
            if(keyword != null)
                boolQueryBuilder.should(ContentFilter.createFilter(keyword));
        }

        this.nativeQueryBuilder.withQuery(q -> q
                .bool(boolQueryBuilder.build())
        );
    }

    @Override
    public NativeQuery getSearch() {
        return this.nativeQueryBuilder.build();
    }
}
>>>>>>> develop-be
