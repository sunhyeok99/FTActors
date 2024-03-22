package com.a602.actors.global.auth.repository;


import com.a602.actors.domain.member.Member;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KakaoMemberRepository extends JpaRepository<Member, String> {
    Optional<Member> findByUserId(String userId);
//    @Query("SELECT m FROM Member m WHERE m.kakaoId = :kakaoId")
//    Optional<Member> findByKakaoId(@Param("kakaoId") String kakaoId);
    Optional<Member> findByKakaoId(String kakaoId);
}
