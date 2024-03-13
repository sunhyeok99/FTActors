package com.a602.actors.domain.profile.repository;

import com.a602.actors.domain.member.QMember;
import com.a602.actors.domain.profile.dto.ProfileRequest;
import com.a602.actors.domain.profile.entity.Profile;
import com.a602.actors.domain.profile.entity.QProfile;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ProfileCustomRepositoryImpl implements ProfileCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager entityManager;

    @Override
    public List<Profile> findAllLatest(int sorting, Character condition, Long memberId) {
        QProfile profile = QProfile.profile;

        //조건에 따라 order by문 변화
        OrderSpecifier<?> orderSpecifier = sorting == 1 ? profile.updatedAt.desc() : profile.updatedAt.asc();
        BooleanBuilder whereClause = new BooleanBuilder();

        //컨디션이 E이면 where절 쓰지 않음
        if(condition != 'E') {
            whereClause.and(profile.type.eq(condition)); //condition에 맞는 조건 넣어주기
        }

        // where 절에 id와 memberId가 같은 경우 추가
        whereClause.and(
                profile.privatePost.eq('F')
                        .or(profile.privatePost.eq('T').and(profile.member.id.eq(memberId)))
        );

        return jpaQueryFactory
                .selectFrom(profile)
                .where(whereClause)
                .orderBy(orderSpecifier)
                .fetch();
    }

    @Override
    public Profile findProfileByIdAndCondition(Long memberId, Character condition) {
        QProfile profile = QProfile.profile;
        BooleanBuilder whereClause = new BooleanBuilder();
        whereClause.and(profile.type.eq(condition));

        return jpaQueryFactory
                .selectFrom(profile)
                .where(profile.member.id.eq(memberId).and(whereClause))
                .fetchOne();
    }

    @Override
    public boolean existProfile(Character condition, Long loginMemberId) {
        QProfile profile = QProfile.profile;

        Profile curProfile = jpaQueryFactory
                .selectFrom(profile)
                .where(profile.type.eq(condition).and(profile.member.id.eq(loginMemberId)))
                .fetchOne();
        if (curProfile == null) return false;

        return true;
    }

    @Override
    public Profile findProfileById(Long profileId) {
        QProfile profile = QProfile.profile;

        return jpaQueryFactory
                .selectFrom(profile)
                .where(profile.id.eq(profileId))
                .fetchOne();
    }

    @Override
    public void updateProfile(Long profileId, ProfileRequest profileRequest) {
        QProfile profile = QProfile.profile;

        JPAUpdateClause updateClause = new JPAUpdateClause(entityManager, profile); //업데이트?

        if (profileRequest.getSelfIntroduction() != null) {
            updateClause.set(profile.content, profileRequest.getSelfIntroduction());
        }

        if (profileRequest.getCondition() != null) {
            updateClause.set(profile.type, profileRequest.getCondition());
        }

        if (profileRequest.getPortfolioLink() != null) {
            updateClause.set(profile.portfolio, profileRequest.getPortfolioLink());
        }

        if(profileRequest.getPrivateProfile() != null) {
            updateClause.set(profile.privatePost, profileRequest.getPrivateProfile());
        }

        updateClause.where(profile.id.eq(profileId)).execute();
    }

}
