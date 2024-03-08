package com.a602.actors.domain.recruitment.service;

import com.a602.actors.domain.apply.repository.ApplyRepository;
import com.a602.actors.domain.member.repository.MemberRepository;
import com.a602.actors.domain.recruitment.dto.RecruitmentRequestDto;
import com.a602.actors.domain.recruitment.dto.RecruitmentResponseDto;
import com.a602.actors.domain.recruitment.entity.Recruitment;
import com.a602.actors.domain.recruitment.repository.RecruitmentRespository;
import com.a602.actors.domain.wishlist.repository.WishlistRepository;
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
public class RecruitmentServiceImpl implements RecruitmentService {

    private final RecruitmentRespository recruitmentRespository;
    private final ApplyRepository applyRepository;
    private final MemberRepository memberRepository;
    private final WishlistRepository wishlistRepository;


    @Override
    @Transactional
    public void register(RecruitmentRequestDto recruitmentRequestDto) {
        Recruitment recruitment = Recruitment.builder()
                .title(recruitmentRequestDto.getTitle())
                .content(recruitmentRequestDto.getContent())
                .member(memberRepository.findById(recruitmentRequestDto.getMemberId()).orElseThrow(() -> new MemberException(ExceptionCodeSet.MEMBER_NOT_FOUND)))
                .category(recruitmentRequestDto.getCategory())
                .image(recruitmentRequestDto.getImage())
                .startDate(recruitmentRequestDto.getStartDate())
                .endDate(recruitmentRequestDto.getEndDate())
                .build();
        recruitmentRespository.save(recruitment);
    }

    @Override
    @Transactional
    public void update(RecruitmentRequestDto recruitmentRequestDto) {
        Recruitment recruitment =recruitmentRespository.findById(recruitmentRequestDto.getId()).orElseThrow(() -> new RecruitmentException(ExceptionCodeSet.RECRUITMENT_NOT_FOUND));
        recruitment.updateRecruitment(recruitmentRequestDto.getTitle(), recruitmentRequestDto.getContent(),
                recruitmentRequestDto.getCategory(), recruitmentRequestDto.getImage(),
                recruitmentRequestDto.getStartDate(), recruitmentRequestDto.getEndDate());
        // 업데이트 완료
    }

    @Override
    @Transactional
    public void updateDate(Long recruitmentId, String endDate) {
        Recruitment recruitment =recruitmentRespository.findById(recruitmentId).orElseThrow(() -> new RecruitmentException(ExceptionCodeSet.RECRUITMENT_NOT_FOUND));
        recruitment.updateEndDate(endDate);
        // 종료날자 업데이트 완료
    }

    @Override
    @Transactional
    // 중간테이블 먼저 삭제 후 삭제
    public void remove(Long recruitmentId) {
//        applyRepository.deleteAllByRecruitmentId(recruitmentId);
//        wishlistRepository.deleteAllByRecruitmentId(recruitmentId);
        recruitmentRespository.deleteAllById(recruitmentId);
    }

    @Override
    @Transactional
    public List<RecruitmentResponseDto> getList() {
        System.out.println(recruitmentRespository.findAll().size());
    List<Recruitment> recruitments = recruitmentRespository.findAll();
    List<RecruitmentResponseDto> recruitmentResponseDtos =recruitments.stream()
            .map(recruitment -> RecruitmentResponseDto.builder()
                    .id(recruitment.getId())
                    .title(recruitment.getTitle())
                    .content(recruitment.getContent())
                    .memberId(recruitment.getMember().getId())
                    .category(recruitment.getCategory())
                    .image(recruitment.getImage())
                    .startDate(recruitment.getStartDate())
                    .endDate(recruitment.getEndDate())
                    .build())
            .collect(Collectors.toList());
        return recruitmentResponseDtos;
    }

    @Override
    @Transactional
    public RecruitmentResponseDto getDetail(Long recruitmentId) {
    Recruitment recruitment = recruitmentRespository.findById(recruitmentId).orElseThrow(() -> new RecruitmentException(ExceptionCodeSet.RECRUITMENT_NOT_FOUND));
    RecruitmentResponseDto recruitmentResponseDto = RecruitmentResponseDto.builder()
            .id(recruitment.getId())
            .title(recruitment.getTitle())
            .content(recruitment.getContent())
            .memberId(recruitment.getMember().getId())
            .category(recruitment.getCategory())
            .image(recruitment.getImage())
            .startDate(recruitment.getStartDate())
            .endDate(recruitment.getEndDate())
            .build();
        return recruitmentResponseDto;
    }

    @Override
    @Transactional
    public List<RecruitmentResponseDto> registerList(Long memberId) {
        List<Recruitment> recruitments =recruitmentRespository.findByMemberId(memberId);
        List<RecruitmentResponseDto> recruitmentResponseDtos =recruitments.stream()
                .map(recruitment -> RecruitmentResponseDto.builder()
                        .id(recruitment.getId())
                        .title(recruitment.getTitle())
                        .content(recruitment.getContent())
                        .memberId(recruitment.getMember().getId())
                        .category(recruitment.getCategory())
                        .image(recruitment.getImage())
                        .startDate(recruitment.getStartDate())
                        .endDate(recruitment.getEndDate())
                        .build())
                .collect(Collectors.toList());
        return recruitmentResponseDtos;
    }

}