package com.a602.actors.domain.admin.repository;

import com.a602.actors.domain.admin.dto.BlackListDto;
import com.a602.actors.domain.admin.dto.QBlackListDto_BlackListSet;
import com.a602.actors.domain.member.Member;
import com.a602.actors.domain.member.QMember;
import com.a602.actors.domain.montage.dto.MontageReportDto;
import com.a602.actors.domain.montage.dto.QMontageReportDto_ReportList;
import com.a602.actors.domain.montage.entity.BlackList;
import com.a602.actors.domain.montage.entity.QBlackList;
import com.a602.actors.domain.montage.entity.QReport;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
class AdminRepositoryImpl implements AdminRepository {

    private final JPAQueryFactory queryFactory;
    private final EntityManager entityManager;

    AdminRepositoryImpl(JPAQueryFactory queryFactory, EntityManager entityManager) {
        this.queryFactory = queryFactory;
        this.entityManager = entityManager;
    }

    @Override
    public List<MontageReportDto.ReportList> getReportList() {

        QReport report = QReport.report;
        QMember member = QMember.member;

        return queryFactory
                    .select(
                        new QMontageReportDto_ReportList(
                                report.id,
                                report.reporter.stageName,
                                report.reportee.stageName,
                                report.reason,
                                report.link
                        )
                    )
                    .from(report)
                    .fetch();
    }

    @Override
    public List<BlackListDto.BlackListSet> getBlackList() {
        QBlackList blackList = QBlackList.blackList;

        return queryFactory.select(
                    new QBlackListDto_BlackListSet(
                            blackList.member.email,
                            blackList.member.name)
                    )
                .from(blackList)
                //.where(blackList.warning.eq('T'))
                .fetch();

    }

    @Override
    @Transactional
    public void acceptReport(Long reportId) throws IllegalArgumentException{

        QReport report = QReport.report;
        QBlackList blackList = QBlackList.blackList;

        QMember member = QMember.member;
        // 블랙리스트에 있다면?

        Long reporteeId = queryFactory.select(member.id)
                .from(report)
                .join(report.reportee, member)
                .where(report.id.eq(reportId))
                .fetchOne();

        log.info("REPORTEE ID : {}", reporteeId);
        log.info("REPORTEE ID : {}", reportId);

        BlackList black = queryFactory
                .selectFrom(blackList)
                .where(blackList.member.id.eq(reporteeId))
                .fetchOne();


        // 이번이 첫 신고
        if (black == null) {
            log.info("첫 신고입니다.");

            Member reportee = entityManager.getReference(Member.class, reporteeId);
            BlackList newBlackList =
                    BlackList.builder()
                            .member(reportee)
                            .warning('F')
                            .build();

            entityManager.persist(newBlackList);
        } else {
            black.setWarning('T');
        }

        // 신고 삭제
        queryFactory
                .delete(report)
                .where(report.id.eq(reportId))
                .execute();

    }

    @Override
    @Transactional
    public void rejectReport(Long reportId) {
        QReport report = QReport.report;

        QBlackList blackList = QBlackList.blackList;

        QMember member = QMember.member;
        // 블랙리스트에 있다면?

        Long reporterId = queryFactory.select(member.id)
                .from(report)
                .join(report.reporter, member)
                .where(report.id.eq(reportId))
                .fetchOne();

        log.info("REPORTEE ID : {}", reporterId);
        log.info("REPORTEE ID : {}", reportId);

        BlackList black = queryFactory
                .selectFrom(blackList)
                .where(blackList.member.id.eq(reporterId))
                .fetchOne();


        // 이번이 첫 신고
        if (black == null) {
            log.info("첫 신고입니다.");

            Member reporter = entityManager.getReference(Member.class, reporterId);
            BlackList newBlackList =
                    BlackList.builder()
                            .member(reporter)
                            .warning('F')
                            .build();

            entityManager.persist(newBlackList);
        } else {
            black.setWarning('T');
        }

        // 신고 삭제
        queryFactory
                .delete(report)
                .where(report.id.eq(reportId))
                .execute();
    }

}