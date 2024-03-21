package com.a602.actors.domain.profile.repository;

import com.a602.actors.domain.member.Member;
import com.a602.actors.domain.member.QMember;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
public class TmpMemRepoImpl {
    private final JPAQueryFactory jpaQueryFactory;


    public Member findByLoginId(String nowLoginId) {
        QMember member = QMember.member;

        return jpaQueryFactory
                .selectFrom(member)
                .where(member.memberId.eq(nowLoginId))
                .fetchOne();
    }
}
