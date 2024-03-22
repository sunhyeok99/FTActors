package com.a602.actors.domain.recruitment.repository;

import com.a602.actors.domain.recruitment.entity.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
//    void deleteAllByRecruitmentId(Long recruitmentId);

    @Query("SELECT w FROM Wishlist w WHERE w.recruitment.id = :recruitmentId and w.member.id = :memberId")
    Wishlist findByRecruitmentIdAndMemberId(long recruitmentId , long memberId);

    @Query("SELECT w FROM Wishlist w WHERE w.member.id = :memberId")
    List<Wishlist> findByMemberId(long memberId);

    void deleteByRecruitmentId(long recruitmentId);

}
