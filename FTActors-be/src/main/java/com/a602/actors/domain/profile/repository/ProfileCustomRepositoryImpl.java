package com.a602.actors.domain.profile.repository;

import com.a602.actors.domain.member.QMember;
import com.a602.actors.domain.profile.entity.Profile;
import com.a602.actors.domain.profile.entity.QProfile;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProfileCustomRepositoryImpl implements ProfileCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Profile> findAllLatest(int sorting, int condition) {
        QProfile profile = QProfile.profile;

        //조건에 따라 order by문 변화
        OrderSpecifier<?> orderSpecifier = sorting == 1 ? profile.updatedAt.desc() : profile.updatedAt.asc();

        return jpaQueryFactory
                .selectFrom(profile)
                .orderBy(orderSpecifier)
                .fetch();
    }

    @Override
    public Profile findProfileById(Long memberId) {
        QProfile profile = QProfile.profile;
        QMember member = QMember.member;

        return jpaQueryFactory
                .selectFrom(profile)
                .fetchOne();
    }

}
