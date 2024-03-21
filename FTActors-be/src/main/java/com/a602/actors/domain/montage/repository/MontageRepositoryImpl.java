package com.a602.actors.domain.montage.repository;

import com.a602.actors.domain.member.Member;
import com.a602.actors.domain.montage.dto.MontageCommentDto;
import com.a602.actors.domain.montage.entity.Comment;
import com.a602.actors.domain.montage.entity.Montage;
import com.a602.actors.domain.montage.entity.QComment;
import com.a602.actors.domain.montage.entity.QMontage;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
public class MontageRepositoryImpl implements MontageRepository {

    private final JPAQueryFactory queryFactory;
    private final EntityManager entityManager;

    public MontageRepositoryImpl(JPAQueryFactory queryFactory, EntityManager entityManager) {
        this.queryFactory = queryFactory;
        this.entityManager = entityManager;
    }

    @Override
    public List<Montage> getAllMontages() {
        QMontage montage = QMontage.montage;

        return queryFactory
                .selectFrom(montage)
                .fetch();
    }

    @Override
    public List<Montage> getMyMontages(Long memberId) {
        QMontage montage = QMontage.montage;

        return queryFactory
                .selectFrom(montage)
                .where(montage.member.id.eq(memberId))
                .fetch();
    }

    @Override
    public Montage getMontage(Long montageId) {
        QMontage montage = QMontage.montage;

        return queryFactory
                .selectFrom(montage)
                .where(montage.id.eq(montageId))
                .fetchOne();
    }

    @Override
    @Transactional
    public void saveMontage(String title, String url){
        Integer memberId = 1; // 추후 JWT 완성되면 고치겠습니다.

        Member member = entityManager.getReference(Member.class, memberId);
        Montage montage =
                Montage.builder()
                .member(member)
                .title(title)
                .link(url)
                .build();

        entityManager.persist(montage);
    }

    @Override
    @Transactional
    public void deleteMontage(Long montageId) {
        QMontage montage = QMontage.montage;

        queryFactory
                .delete(montage)
                .where(montage.id.eq(montageId))
                .execute();
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
                            .isDeleted(req.getIsDeleted())
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

    @Override
    @Transactional
    public void deleteComment(Long montageId, Long commentId){

        QComment comment = QComment.comment;

        // 대댓글(자식 댓글)을 구한다.
        List<Comment> commentList =
                queryFactory.selectFrom(comment)
                        .where(comment.referenceId.eq(commentId))
                        .fetch();

        if(!commentList.isEmpty()){ // 대댓글이 존재한다면, 댓글만 삭제한다.
            Comment targetComment = entityManager.find(Comment.class, commentId);
            targetComment.setIsDeleted(true);
            log.info("id : {}, state : {}", targetComment.getId(), targetComment.isDeleted());
            //entityManager.merge(targetComment);
        }
        else{ // 대댓글이 존재하지 않으면 댓글 삭제
            queryFactory.delete(comment)
                    .where(comment.id.eq(commentId).and(comment.montage.id.eq(montageId)))
                    .execute();
        }

    }


}
