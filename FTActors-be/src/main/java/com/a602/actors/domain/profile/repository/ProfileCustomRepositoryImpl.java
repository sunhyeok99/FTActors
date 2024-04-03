package com.a602.actors.domain.profile.repository;

import com.a602.actors.domain.profile.dto.ProfileRequest;
import com.a602.actors.domain.profile.entity.Profile;
import com.a602.actors.domain.profile.entity.QProfile;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ProfileCustomRepositoryImpl implements ProfileCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager entityManager;

    @Override
//    public Profile findProfileByIdAndCondition(Long profileId, Long loginnedId) {
    public Optional<Profile> findProfileByIdAndCondition(Long profileId, Long loginnedId) {
        QProfile profile = QProfile.profile;

        // profileId와 일치하는 Profile을 조회하고,
        // 그 중 privatePost가 'F'이거나 memberId가 loginnedId와 일치하는 경우에만 반환
        return Optional.ofNullable(jpaQueryFactory
                .selectFrom(profile)
                .where(profile.id.eq(profileId)
                        .and(profile.privatePost.eq('F')
                                .or(profile.member.id.eq(loginnedId))))
                .fetchOne());

//        return result;
    }

    @Override // 생성 - 이미 있는 프로필인지 확인
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

        if (profileRequest.getContent() != null) {
            updateClause.set(profile.content, profileRequest.getContent());
        }

        if (profileRequest.getType() != null) {
            updateClause.set(profile.type, profileRequest.getType());
        }

        if(profileRequest.getPrivateProfile() != null) {
            updateClause.set(profile.privatePost, profileRequest.getPrivateProfile());
        }

        updateClause.set(profile.updatedAt, LocalDateTime.now());

        updateClause.where(profile.id.eq(profileId)).execute();
    }

    @Override
    public Long existActorInMyPage(@RequestParam(name = "memberId") Long memberId) {
        QProfile profile = QProfile.profile;

        return jpaQueryFactory
                .select(profile)
                .from(profile)
                .where(profile.member.id.eq(memberId)
                        .and(profile.type.eq('A')))
                .fetchCount();
    }

    @Override
    public Long existPDInMyPage(@RequestParam(name = "memberId") Long memberId) {
        QProfile profile = QProfile.profile;

        return jpaQueryFactory
                .select(profile)
                .from(profile)
                .where(profile.member.id.eq(memberId)
                        .and(profile.type.eq('P')))
                .fetchCount();
    }

}
