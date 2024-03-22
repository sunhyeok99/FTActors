package com.a602.actors.domain.follow.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "follow")
public class Follow {
    // member에서의 정보가 바뀐다고 follow의 두개모두 바뀌는게 아니어서
    // 유저와 follow 객체의 관계가 아니라 (user에서 불러오는 과정에서 N+1문제 해결 불가능함)
    // follow와 following의 상태로 복합키 사용 (Member에서 id필드값만 가지고옴)

    @EmbeddedId
    private FollowId followId;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;


    @Builder
    public Follow(Long followerId , Long followingId, LocalDateTime createdAt, LocalDateTime updatedAt){
        this.followId = new FollowId(followingId, followerId);
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }


}
