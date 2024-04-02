package com.a602.actors.global.jwt.repository;

import com.a602.actors.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JWTMemberRepository extends JpaRepository<Member, String> {
    Optional<Member> findByLoginId(String loginId);
}
