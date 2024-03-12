package com.a602.actors.domain.montage.repository;

import com.a602.actors.domain.member.Member;
import com.a602.actors.domain.montage.dto.MontageCommentDto;
import com.a602.actors.domain.montage.entity.Comment;
import com.a602.actors.domain.montage.entity.Montage;
import com.a602.actors.domain.montage.entity.QComment;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MontageCommentRepositoryImpl implements MontageCommentRepository {

    private final JPAQueryFactory queryFactory;
    private final EntityManager entityManager;

    public MontageCommentRepositoryImpl(JPAQueryFactory queryFactory, EntityManager entityManager) {
        this.queryFactory = queryFactory;
        this.entityManager = entityManager;
    }

    public List<Comment> findCommentAndReplies(Long montageId){
        // 별칭을 따로 줘야한다~
        QComment comment1 = QComment.comment;
        QComment comment2 = QComment.comment;

        return queryFactory
                .selectFrom(comment1)
                .leftJoin(comment2)
                .on(comment1.id.eq(comment2.referenceId))
                .fetchJoin()
                .where(comment1.montage.id.eq(montageId))
                .fetch(); // Member가.. 그건갑네
    }

    @Override
    @Transactional
    public void saveComment(MontageCommentDto.CreateRequest req) {
        // FIX : memberId의 경우 토큰 개발 후 코드에서 가져오는 것으로 로직 변경 수행
        Member member = entityManager.getReference(Member.class, 1);
        Montage montage = entityManager.getReference(Montage.class, req.getMontageId());

        Comment newComment = Comment
                            .builder()
                .member(member)
                .montage(montage)
                .content(req.getContent())
                .referenceId(req.getParentId())
                .build();

        entityManager.persist(newComment);
    }

    @Override
    @Transactional
    public void updateComment(MontageCommentDto.UpdateRequest req) {
        // FIX : memberId의 경우 토큰 개발 후 코드에서 가져오는 것으로 로직 변경 수행
        Comment comment = entityManager.find(Comment.class, req.getCommentId());

        comment.setContent(req.getContent());
    }


}
