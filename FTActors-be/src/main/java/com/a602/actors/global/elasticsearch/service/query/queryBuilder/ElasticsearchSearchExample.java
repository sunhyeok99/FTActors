//import org.elasticsearch.index.query.BoolQueryBuilder;
//import org.elasticsearch.index.query.QueryBuilders;
//import org.elasticsearch.search.builder.SearchSourceBuilder;
//import org.elasticsearch.search.SearchRequest;
//
//import static co.elastic.clients.elasticsearch._types.query_dsl.Query.Kind.Bool;
//
//public class ElasticsearchSearchExample {
//    public SearchRequest buildSearchRequest(String[] keywords) {
//        // Bool Query 생성
//        Bool. boolQuery = QueryBuilders.boolQuery();
//
//        // 각 키워드에 대해 검색 조건 추가
//        for (String keyword : keywords) {
//            boolQuery.must(QueryBuilders.matchQuery("content", keyword));
//        }
//
//        // SearchRequest 생성 및 Bool Query 할당
//        SearchRequest searchRequest = new SearchRequest();
//        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
//        sourceBuilder.query(boolQuery);
//        searchRequest.source(sourceBuilder);
//
//        return searchRequest;
//    }
//}
