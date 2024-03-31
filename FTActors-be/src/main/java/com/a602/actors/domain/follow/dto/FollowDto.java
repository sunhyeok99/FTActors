package com.a602.actors.domain.follow.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class FollowDto {
    private Long followingId;
    private Long followerId;
    private String memberName;
    private int follow;

    @Builder
    public FollowDto(
            Long followingId,
            Long followerId,
            String memberName,
            int follow
    ) {
    this.followingId = followingId;
    this.followerId = followerId;
    this.memberName = memberName;
    this.follow = follow;
    }

}
