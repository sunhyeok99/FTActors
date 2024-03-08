package com.a602.actors.domain.profile.repository;

import com.a602.actors.domain.profile.entity.Profile;
import com.a602.actors.domain.profile.entity.QProfile;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProfileCustomRepositoryImpl implements ProfileCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Profile findById(Long memberId) {
        QProfile profile = QProfile.profile;

        return jpaQueryFactory
                .selectFrom(profile)
                .where(profile.memberId.eq(memberId))
                .fetchOne();
    }
}
