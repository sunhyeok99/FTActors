package com.a602.actors.domain.montage.repository;

import com.a602.actors.domain.member.Member;
import com.a602.actors.domain.member.QMember;
import com.a602.actors.domain.montage.dto.MontageCommentDto;
import com.a602.actors.domain.montage.dto.MontageDto;
import com.a602.actors.domain.montage.dto.QMontageDto_Montages;
import com.a602.actors.domain.montage.entity.*;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Slf4j
public class MontageRepositoryImpl implements MontageRepository {

    private final JPAQueryFactory queryFactory;
    private final EntityManager entityManager;

    public MontageRepositoryImpl(JPAQueryFactory queryFactory, EntityManager entityManager) {
        this.queryFactory = queryFactory;
        this.entityManager = entityManager;
    }

    // 몽타주 개발
    @Override
    public List<MontageDto.Montages> getAllMontages() {
        QMontage montage = QMontage.montage;
        QLikeCount likeCount = QLikeCount.likeCount;

        // montage에 따라 likeCount 생성

        return queryFactory
                        .select(new QMontageDto_Montages(
                                montage.title,
                                montage.link,
                                Expressions.as(
                                    JPAExpressions.
                                            select(likeCount.count())
                                            .from(likeCount)
                                            .where(likeCount.montage.id.eq(montage.id)), "likeCount")
                                , montage.createdAt, montage.updatedAt)
                                )
                        .from(montage)
                    // 설정된 데이터를 가지고 JPQL 생성 및 실행하는 메소드
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
    public Optional<Montage> getMontage(Long montageId) {
        QMontage montage = QMontage.montage;

        return Optional.ofNullable(queryFactory
                .selectFrom(montage)
                .where(montage.id.eq(montageId))
                .fetchOne());
    }

    public Optional<Comment> getComment(Long montageId, Long commentId) {
        QMontage montage = QMontage.montage;
        QComment comment = QComment.comment;

        return Optional.ofNullable(queryFactory
                .selectFrom(comment)
                .where(montage.id.eq(montageId).and(comment.id.eq(commentId)))
                .fetchOne());
    }


    @Override
    @Transactional
    public void saveMontage(String originalName, String savedName, String url){
        Integer memberId = 1; // 추후 JWT 완성되면 고치겠습니다.

        Member member = entityManager.getReference(Member.class, memberId);
        Montage montage =
                Montage.builder()
                .member(member)
                .title(originalName)
                        .savedName(savedName)
                .link(url)
                .build();

        entityManager.persist(montage);
    }

    // 좋아요 개발
    @Override
    @Transactional
    public boolean addLike(Long montageId, Long memberId) {
        QLikeCount likeCount = QLikeCount.likeCount;

        LikeCount likeTrace = queryFactory
                .selectFrom(likeCount)
                        .where(likeCount.member.id.eq(memberId)
                                .and(likeCount.montage.id.eq(montageId)))
                .fetchOne();

        // 좋아요를 누른 흔적이 있을 때
        if(likeTrace != null){

            Long affectedRow =
                    queryFactory
                    .delete(likeCount)
                    .where(likeCount.member.id.eq(memberId)
                            .and(likeCount.montage.id.eq(montageId)))
                    .execute();

            log.info("LIKE DELETE - affected ROW : {} ", affectedRow);
            return false;
        }
        else{
            // 좋아요를 누른 흔적이 없을 때
            Member member = entityManager.getReference(Member.class, memberId);
            Montage montage = entityManager.getReference(Montage.class, montageId);

            LikeCount newLikeCount =
                    LikeCount.builder()
                            .member(member)
                            .montage(montage)
                            .build();

            entityManager.persist(newLikeCount);
        }
        return true;
    }

    // Comment 개발

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

    @Override
    @Transactional
    public void addReport(Long reporterId, Long montageId, String reason, String link) {

        QMember member = QMember.member;
        QMontage montage = QMontage.montage;

        // 신고당한 사람 아이디를 얻음
        Member reportee = queryFactory
                .select(member)
                .from(montage)
                .innerJoin(montage.member, member)
                .where(montage.id.eq(montageId))
                .fetchOne();

        Member reporter = entityManager.getReference(Member.class, reporterId);

        log.info("신고당한 사람의 정보 : " + reportee);
        log.info("신고한 사람의 정보 : " + reporter);

        Report report =
                Report.builder()
                        .reporter(reporter)
                        .reportee(reportee)
                        .reason(reason)
                        .link(link)
                        .build();

        entityManager.persist(report);
    }


    @Override
    @Transactional
    public void addCommentReport(Long reporterId, Long montageId, Long commentId, String reason, String link) {

        QMember member = QMember.member;
        QMontage montage = QMontage.montage;
        QComment comment = QComment.comment;

        // 신고당한 사람 아이디를 얻음
        Member reportee = queryFactory
                .select(member)
                .from(comment)
                .innerJoin(montage.member, member)
                .where(montage.id.eq(montageId).and(comment.id.eq(commentId)))
                .fetchOne();

        Member reporter = entityManager.getReference(Member.class, reporterId);

        log.info("신고당한 사람의 정보 : " + reportee);
        log.info("신고한 사람의 정보 : " + reporter);

        Report report =
                Report.builder()
                        .reporter(reporter)
                        .reportee(reportee)
                        .reason(reason)
                        .link(link)
                        .build();

        entityManager.persist(report);
    }

}
