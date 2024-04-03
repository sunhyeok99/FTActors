<<<<<<< HEAD
package com.a602.actors.global.elasticsearch.filter;

import org.springframework.data.elasticsearch.client.elc.NativeQuery;

import java.util.List;

public interface QueryBuilderInterface {
    void createContentQuery(List<String> keywords);

//    void createNameQuery(String keyword);

    NativeQuery getSearch();
}
=======
package com.a602.actors.global.elasticsearch.filter;

import org.springframework.data.elasticsearch.client.elc.NativeQuery;

import java.util.List;

public interface QueryBuilderInterface {
    void createQuery(List<String> keywords);

    void createQuery(String keyword);

    NativeQuery getSearch();
}
>>>>>>> develop-be
