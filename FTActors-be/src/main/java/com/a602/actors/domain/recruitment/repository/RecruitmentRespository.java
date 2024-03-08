package com.a602.actors.domain.recruitment.repository;

import com.a602.actors.domain.recruitment.entity.Recruitment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecruitmentRespository extends JpaRepository<Recruitment, Long> {

    void deleteAllById(Long recruitmentId);

    List<Recruitment> findByMemberId(Long memberId);


}
