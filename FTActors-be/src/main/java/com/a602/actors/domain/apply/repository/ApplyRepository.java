package com.a602.actors.domain.apply.repository;

import com.a602.actors.domain.apply.entity.Apply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplyRepository extends JpaRepository<Apply, Long> {

//    void deleteAllByRecruitmentId(Long recruitmentId);
}
