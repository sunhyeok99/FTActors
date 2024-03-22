package com.a602.actors.domain.follow.repository;

import com.a602.actors.domain.follow.entity.Follow;
import com.a602.actors.domain.follow.entity.FollowId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowRepository extends JpaRepository<Follow, FollowId> {

    @Query("SELECT f From Follow f WHERE f.followId.followingId = :followingId")
    List<Follow> findByFollowingId(Long followingId);

    @Query("SELECT f From Follow f WHERE f.followId.followerId = :followerId")
    List<Follow> findByFollowerId(Long followerId);

    @Query("SELECT f From Follow f WHERE f.followId.followingId = :followingId And f.followId.followerId = :followerId")
    Follow findByFollowingIdAndFollowerId(Long followingId, Long followerId);

    void deleteByFollowId_FollowingIdAndFollowId_FollowerId(Long followingId, Long followerId);

    @Query("Select count(*) From Follow f WHERE f.followId.followingId = :followingId")
    Long countByFollowingId(Long followingId);

    @Query("Select count(*) From Follow f WHERE f.followId.followerId = :followerId")
    Long countByFollowerId(Long followerId);
}
