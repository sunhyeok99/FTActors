package com.a602.actors.domain.montage.repository;

import com.a602.actors.domain.montage.entity.Comment;
import com.a602.actors.domain.montage.entity.QComment;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MontageCommentRepositoryImpl {

    private final JPAQueryFactory queryFactory;

    public MontageCommentRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    public List<Comment> findCommentAndReplies(Long montageId){

        // 별칭을 따로 줘야한다~
        QComment comment1 = QComment.comment;
        QComment comment2 = QComment.comment;

        //return null;

        return queryFactory
                .selectFrom(comment1)
                .leftJoin(comment2)
                .on(comment1.id.eq(comment2.referenceId))
                .fetchJoin()
                .where(comment1.montage.id.eq(montageId))
                .fetch(); // Member가.. 그건갑네
    }
}
