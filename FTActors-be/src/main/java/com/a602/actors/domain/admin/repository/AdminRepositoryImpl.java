package com.a602.actors.domain.admin.repository;

import com.a602.actors.domain.montage.entity.BlackList;
import com.a602.actors.domain.montage.entity.QBlackList;
import com.a602.actors.domain.montage.entity.QReport;
import com.a602.actors.domain.montage.entity.Report;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
class AdminRepositoryImpl implements AdminRepository {

    private final JPAQueryFactory queryFactory;
    private final EntityManager entityManager;

    AdminRepositoryImpl(JPAQueryFactory queryFactory, EntityManager entityManager) {
        this.queryFactory = queryFactory;
        this.entityManager = entityManager;
    }

    @Override
    public List<Report> getReportList() {

        QReport report = QReport.report;

        return queryFactory
                .selectFrom(report)
                .fetch();
    }

    @Override
    public List<BlackList> getBlackList() {
        QBlackList blackList = QBlackList.blackList;

        return new ArrayList<>();
    }

    @Override
    public void acceptReport() {
        // 테이블에서 해당 내역 지우고


        // reporter 블랙리스트 테이블에 추가
        // 만일 존재하면 warning True로 변경

    }

    @Override
    public void rejectReport() {
        // 테이블에서 해당 내역 지우고

        // reportee 블랙리스트 테이블에 추가
        // 만일 존재하면 warning True로 변경
    }
}