package com.a602.actors.domain.follow.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Embeddable
@NoArgsConstructor
@Getter
public class FollowId implements Serializable {


    @JoinColumn(name = "following_id")
    private Long followingId;

    @JoinColumn(name = "follower_id")
    private Long followerId;

    @Builder
    public FollowId(Long followingId , Long followerId){
        this.followingId = followingId;
        this.followerId = followerId;
    }

}
