package com.a602.actors.domain.apply.repository;

import com.a602.actors.domain.apply.entity.Apply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplyRepository extends JpaRepository<Apply, Long> {

    @Query("SELECT a From Apply a WHERE a.recruitment.id = :recruitmentId and a.member.id = :memberId")
    Apply findByRecruitmentIdAndMemberId(@Param("recruitmentId") Long recruitmentId, @Param("memberId") Long memberId);

    void deleteByRecruitmentIdAndMemberId(@Param("recruitmentId") Long recruitmentId, @Param("memberId") Long memberId);

    @Query("SELECT a FROM Apply a WHERE a.member.id = :memberId")
    List<Apply> findByMemberId(@Param("memberId") Long memberId);


    @Query("SELECT a FROM Apply a WHERE a.recruitment.id = :recruitmentId")
    List<Apply> findByRecruitmentId(@Param("recruitmentId") Long recruitmentId);
}


