package com.a602.actors.domain.montage.repository;

import com.a602.actors.domain.montage.entity.Montage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MontageResourceRepository extends JpaRepository<Montage, Long> {

    List<Montage> findAll();
    //List<Montage> findByMemberId(Integer memberId);
}
