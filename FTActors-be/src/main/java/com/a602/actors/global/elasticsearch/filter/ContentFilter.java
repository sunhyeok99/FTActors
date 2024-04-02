package com.a602.actors.global.elasticsearch.filter;

import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.QueryVariant;
import co.elastic.clients.elasticsearch._types.query_dsl.TermQuery;

public class ContentFilter {
    public static Query createFilter(String keyword) {
        QueryVariant termContentQuery = new TermQuery.Builder()
                .field("content")
                .value(keyword)
                .build();
        return new Query(termContentQuery);
    }
}
