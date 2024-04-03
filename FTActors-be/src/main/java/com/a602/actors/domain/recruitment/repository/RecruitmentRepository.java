package com.a602.actors.domain.recruitment.repository;

import com.a602.actors.domain.recruitment.entity.Recruitment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecruitmentRepository extends JpaRepository<Recruitment, Long> {

    @Query("SELECT r FROM Recruitment r "+
            " WHERE r.member.id = :memberId")
    List<Recruitment> findByMemberId(@Param("memberId") Long memberId);

    @Query("SELECT r, w.id FROM Recruitment r left join Wishlist w on r.id = w.recruitment.id And :memberId = w.member.id WHERE r.privateRecruitment = 'F'")
    List<Object[]> findAllRecruitment(@Param("memberId") Long memberId);

    @Query("SELECT r FROM Recruitment r " +
            " WHERE r.endDate < :currentDate AND r.privateRecruitment = 'F'")
    List<Recruitment> findByActivatedRecruitment(@Param("currentDate") String currentDate);
    
    // 위시리스트만 가져오는 함수를 만들기
    @Query("SELECT r, w.id FROM Recruitment r inner join Wishlist w on r.id = w.recruitment.id And :memberId = w.member.id WHERE r.privateRecruitment = 'F'")
    List<Recruitment> findWishlistOnly(Long memberId);

    // 시작일을 기준으로 내림차순으로 정렬된 모든 공고를 반환
    List<Recruitment> findAllByOrderByStartDateDesc();


}
