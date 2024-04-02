package com.a602.actors.domain.follow.service;

import static com.a602.actors.global.exception.ExceptionCodeSet.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.a602.actors.domain.follow.dto.FollowDto;
import com.a602.actors.domain.follow.entity.Follow;
import com.a602.actors.domain.follow.repository.FollowRepository;
import com.a602.actors.domain.member.Member;
import com.a602.actors.domain.member.repository.MemberRepository;
//import com.a602.actors.domain.notification.document.Notify;
//import com.a602.actors.domain.notification.service.NotificationService;
import com.a602.actors.global.exception.MemberException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class FollowServiceImpl implements FollowService{

    private final FollowRepository followRepository;
    private final MemberRepository memberRepository;
//    private final NotificationService notificationService;

    @Override
    @Transactional
    public String follow(Long followingId, Long followerId) {
        Follow follow = followRepository.findByFollowingIdAndFollowerId(followingId,followerId);
        if(follow == null){
            follow = Follow.builder()
                    .followingId(followingId)
                    .followerId(followerId)
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();
            followRepository.save(follow);

            // followingId가 followerId를 팔로잉한다.
            Member member = memberRepository.findById(followingId)
                .orElseThrow(() -> new MemberException(MEMBER_NOT_FOUND));

//            notificationService.send(followerId, Notify.NotificationType.FOLLOW, member.getStageName() + "님이 팔로우 했습니다.");

            return "팔로우 성공";
        }
        followRepository.deleteByFollowId_FollowingIdAndFollowId_FollowerId(followingId, followerId);
        return "팔로우 삭제 성공";
    }

    @Override
    @Transactional
    public List<FollowDto> followingList(Long followingId) {
        List<Follow> follows = followRepository.findByFollowingId(followingId);

        // Follow 엔티티를 FollowDto로 변환하여 리스트에 담음
        List<FollowDto> followDtoList = follows.stream()
                .map(follow -> FollowDto.builder()
                        .followingId(followingId)
                        .followerId(follow.getFollowId().getFollowerId())
                        .memberName(memberRepository.findById(follow.getFollowId().getFollowerId()).orElseThrow(() -> new MemberException(
                            MEMBER_NOT_FOUND)).getName())
                        .follow(1)
                        .build())
                .collect(Collectors.toList());

        return followDtoList;
    }

    @Override
    @Transactional
    public List<FollowDto> followerList(Long followerId) {
        List<Follow> follows = followRepository.findByFollowerId(followerId);
        // Follow 엔티티를 FollowDto로 변환하여 리스트에 담습니다.
        List<FollowDto> followDtoList = follows.stream()
                .map(follow -> FollowDto.builder()
                        .followingId(follow.getFollowId().getFollowingId())
                        .followerId(followerId)
                        .memberName(memberRepository.findById(follow.getFollowId().getFollowingId()).orElseThrow(() -> new MemberException(
                            MEMBER_NOT_FOUND)).getName())
                        .follow(followDetail(followerId,follow.getFollowId().getFollowingId()))
                        .build())
                .collect(Collectors.toList());

        return followDtoList;
    }

    @Override
    @Transactional
    public int followDetail(Long followingId, Long followerId) {
        Follow follow = followRepository.findByFollowingIdAndFollowerId(followingId, followerId);
        if(follow == null){
            return 0;
        }
        return 1;
    }

    @Override
    @Transactional
    public Long followingNum(Long followingId) {
        return followRepository.countByFollowingId(followingId);
    }

    @Override
    @Transactional
    public Long followerNum(Long followerId) {
        return followRepository.countByFollowerId(followerId);
    }
}
