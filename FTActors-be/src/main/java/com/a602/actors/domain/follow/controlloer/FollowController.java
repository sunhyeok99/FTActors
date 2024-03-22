package com.a602.actors.domain.follow.controlloer;

import com.a602.actors.domain.follow.dto.FollowDto;
import com.a602.actors.domain.follow.service.FollowService;
import com.a602.actors.global.common.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/follow")
@RestController
public class FollowController {

    private final FollowService followService;

    @PutMapping("")
    public ApiResponse<String> following(@RequestParam Long followingId, @RequestParam Long followerId){
        return new ApiResponse<>(HttpStatus.OK.value() , "변경 성공", followService.follow(followingId, followerId));
    }

    @GetMapping("followingList")
    public ApiResponse<List<FollowDto>> followingList(@RequestParam Long followingId){
        return new ApiResponse<>(HttpStatus.OK.value(), "팔로잉 리스트를 불러옵니다" , followService.followingList(followingId));
    }

    @GetMapping("followerList")
    public ApiResponse<List<FollowDto>> followerList(@RequestParam Long followerId){
        return new ApiResponse<>(HttpStatus.OK.value(), "팔로워 리스트를 불러옵니다/ follow값 1이면 나도 팔로잉하고 있다는 의미" , followService.followerList(followerId));
    }
    
    @GetMapping("detail")
    public ApiResponse<Integer> followDetail(@RequestParam Long followingId , @RequestParam Long followerId){
        return new ApiResponse<>(HttpStatus.OK.value(), "1이면 팔로우상태, 0이면 관계없음", followService.followDetail(followingId, followerId));
    }
    @GetMapping("followingNum")
    public ApiResponse<Long> followingNum(@RequestParam Long followingId) {
        return new ApiResponse<>(HttpStatus.OK.value(), "내가 팔로잉한 멤버의 수" , followService.followingNum(followingId));
    }

    @GetMapping("followerNum")
    public ApiResponse<Long> followerNum(@RequestParam Long followerId) {
        return new ApiResponse<>(HttpStatus.OK.value(), "나를 팔로잉한 멤버의 수" , followService.followerNum(followerId));
    }

}
