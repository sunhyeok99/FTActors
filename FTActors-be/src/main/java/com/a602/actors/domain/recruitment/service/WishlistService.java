package com.a602.actors.domain.recruitment.service;

import com.a602.actors.domain.recruitment.dto.RecruitmentListResponseDto;

import java.util.List;

public interface WishlistService {

    // 1. 찜 등록/ 해제
    public int wishlist(long recruitmentId , long memberId);

    // 2. 찜 여부
    public int detail(long recruitmentId , long memberId);

    // 3. 찜 리스트
    public List<RecruitmentListResponseDto> list(long memberId);

}
