package com.a602.actors.domain.follow.repository;

import com.a602.actors.domain.follow.entity.Follow;
import com.a602.actors.domain.follow.entity.FollowId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowRepository extends JpaRepository<Follow, FollowId> {

    @Query("SELECT f From Follow f WHERE f.followId.followingId = :followingId")
    List<Follow> findByFollowingId(@Param("followingId") Long followingId);

    @Query("SELECT f From Follow f WHERE f.followId.followerId = :followerId")
    List<Follow> findByFollowerId(@Param("followerId") Long followerId);

    @Query("SELECT f From Follow f WHERE f.followId.followingId = :followingId And f.followId.followerId = :followerId")
    Follow findByFollowingIdAndFollowerId(@Param("followerId") Long followingId, @Param("followingId") Long followerId);

    void deleteByFollowId_FollowingIdAndFollowId_FollowerId(@Param("followingId") Long followingId, @Param("followerId") Long followerId);

    @Query("Select count(*) From Follow f WHERE f.followId.followingId = :followingId")
    Long countByFollowingId(@Param("followingId") Long followingId);

    @Query("Select count(*) From Follow f WHERE f.followId.followerId = :followerId")
    Long countByFollowerId(@Param("followerId") Long followerId);
}
