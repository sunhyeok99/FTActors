package com.a602.actors.domain.recruitment.repository;

import com.a602.actors.domain.recruitment.entity.Recruitment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecruitmentRepository extends JpaRepository<Recruitment, Long>      {

    @Query("SELECT r FROM Recruitment r "+
            " WHERE r.member.id = :memberId")
    List<Recruitment> findByMemberId(Long memberId);

    @Query("SELECT r FROM Recruitment r " +
    "WHERE r.privateRecruitment = 'F'")
    List<Recruitment> findAllRecruitment();

    @Query("SELECT r FROM Recruitment r " +
            " WHERE r.endDate < :currentDate AND r.privateRecruitment = 'F'")
    List<Recruitment> findByActivatedRecruitment(String currentDate);

}
