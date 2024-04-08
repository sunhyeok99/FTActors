package com.a602.actors.domain.recruitment.service;

import com.a602.actors.domain.member.repository.MemberRepository;
import com.a602.actors.domain.recruitment.dto.RecruitmentListResponseDto;
import com.a602.actors.domain.recruitment.entity.Recruitment;
import com.a602.actors.domain.recruitment.entity.Wishlist;
import com.a602.actors.domain.recruitment.repository.RecruitmentRepository;
import com.a602.actors.domain.recruitment.repository.WishlistRepository;
import com.a602.actors.global.exception.ExceptionCodeSet;
import com.a602.actors.global.exception.MemberException;
import com.a602.actors.global.exception.RecruitmentException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class WishlistServiceImpl implements WishlistService {

    private final WishlistRepository wishlistRepository;
    private final RecruitmentRepository recruitmentRepository;
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public int wishlist(long recruitmentId, long memberId) {
        Wishlist wishlist = wishlistRepository.findByRecruitmentIdAndMemberId(recruitmentId, memberId);
        if (wishlist == null) {
            wishlist = Wishlist.builder()
                    .recruitment(recruitmentRepository.findById(recruitmentId).orElseThrow(() -> new RecruitmentException(ExceptionCodeSet.RECRUITMENT_NOT_FOUND)))
                    .member(memberRepository.findById(memberId).orElseThrow(() -> new MemberException(ExceptionCodeSet.MEMBER_NOT_FOUND)))
                    .build();
            wishlistRepository.save(wishlist);
            return 1;
        }
        wishlistRepository.deleteById(wishlist.getId());
        return 0;
    }

    @Override
    @Transactional
    public int detail(long recruitmentId, long memberId) {
        Wishlist wishlist = wishlistRepository.findByRecruitmentIdAndMemberId(recruitmentId, memberId);
        if (wishlist == null) {
            return 0;
        }
        return 1;
    }

    // 멤버 아이디로 먼저 위시리스트 필터링 -> 위시리스트의 공고 id를 이용해서 RecruitmentList~ 생성
    @Override
    @Transactional
    public List<RecruitmentListResponseDto> list(long memberId) {
        List<Wishlist> wishlists = wishlistRepository.findByMemberId(memberId);
        // 위시리스트의 공고 id를 이용해서 RecruitmentListDto 생성
        List<RecruitmentListResponseDto> recruitmentListResponseDtos = wishlists.stream()
                .map(wishlist -> {
                    Recruitment recruitment = wishlist.getRecruitment();
                    return RecruitmentListResponseDto.builder()
                            .id(recruitment.getId())
                            .title(recruitment.getTitle())
                            .image(recruitment.getImage())
                            .endDate(recruitment.getEndDate())
                            .wishlist(1)
                            .privateRecruitment(recruitment.getPrivateRecruitment())
                            .build();
                })
                .collect(Collectors.toList());
        return recruitmentListResponseDtos;

    }
}
