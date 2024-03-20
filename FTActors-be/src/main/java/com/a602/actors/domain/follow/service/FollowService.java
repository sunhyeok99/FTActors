package com.a602.actors.domain.follow.service;

import com.a602.actors.domain.follow.dto.FollowDto;

import java.util.List;

public interface FollowService {
    
    
    // 1. 팔로우 관계 변경 
    String follow(Long followingId , Long followerId);
    // 2. 팔로잉 리스트 
    List<FollowDto> followingList(Long followingId);
    // 3. 팔로워 리스트
    List<FollowDto> followerList(Long followerId);
    // 4. 팔로잉 여부
    int followDetail(Long followingId, Long followerId);

    Long followingNum(Long followingId);

    Long followerNum(Long followerId);
}
