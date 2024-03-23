//package com.a602.actors.global.elasticsearch.service;
//
//import com.a602.actors.domain.apply.entity.Apply;
//import com.a602.actors.domain.apply.repository.ApplyRepository;
//import com.a602.actors.domain.apply.service.ApplyService;
//import com.a602.actors.domain.member.repository.MemberRepository;
//import com.a602.actors.domain.recruitment.dto.RecruitmentListResponseDto;
//import com.a602.actors.domain.recruitment.dto.RecruitmentRequestDto;
//import com.a602.actors.domain.recruitment.dto.RecruitmentResponseDto;
//import com.a602.actors.domain.recruitment.entity.Recruitment;
//import com.a602.actors.domain.recruitment.repository.RecruitmentRepository;
//import com.a602.actors.domain.recruitment.repository.WishlistRepository;
//import com.a602.actors.domain.recruitment.service.WishlistService;
//import com.a602.actors.global.common.config.FileUtil;
//import com.a602.actors.global.common.enums.FolderType;
//import com.a602.actors.global.exception.ExceptionCodeSet;
//import com.a602.actors.global.exception.MemberException;
//import com.a602.actors.global.exception.RecruitmentException;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@RequiredArgsConstructor
//@Transactional(readOnly = true)
//@Service
//public class RecruitmentElasticServiceImpl implements RecruitmentElasticService {
//
//    private final RecruitmentRepository recruitmentRepository;
////    private final ApplyRepository applyRepository;
////    private final ApplyService applyService;
//    private final MemberRepository memberRepository;
////    private final WishlistRepository wishlistRepository;
////    private final WishlistService wishlistService;
//
//    @Override
//    @Transactional //o
//    public void registerInElastic(RecruitmentRequestDto recruitmentRequestDto) throws IOException {
//
//    }
//
////    @Override
////    @Transactional //o
////    public void update(RecruitmentRequestDto recruitmentRequestDto) throws IOException {
//////        Recruitment recruitment = recruitmentRepository.findById(recruitmentRequestDto.getId()).orElseThrow(() -> new RecruitmentException(ExceptionCodeSet.RECRUITMENT_NOT_FOUND));
//////       // S3에서 이미지 먼저 삭제 후 다시 들어온 이미지로 교체
//////        FileUtil.deleteFile(recruitment.getSavedName() , FolderType.RECRUIT_PATH);
//////
//////        String savedName = FileUtil.makeFileName(recruitmentRequestDto.getTitle());
//////
//////        String imageurl = FileUtil.uploadFile(recruitmentRequestDto.getImage(), savedName, FolderType.RECRUIT_PATH);
//////        recruitment.updateRecruitment(recruitmentRequestDto.getTitle(), recruitmentRequestDto.getContent(),
//////                recruitmentRequestDto.getCategory(), imageurl, savedName,
//////                recruitmentRequestDto.getStartDate(), recruitmentRequestDto.getEndDate());
//////        // 업데이트 완료
////    }
//
////    @Override
////    @Transactional //o
////    public void updateDate(Long recruitmentId, String endDate) {
//////        Recruitment recruitment = recruitmentRepository.findById(recruitmentId).orElseThrow(() -> new RecruitmentException(ExceptionCodeSet.RECRUITMENT_NOT_FOUND));
//////        recruitment.updateEndDate(endDate);
//////        // 종료날자 업데이트 완료
////    }
//
////    @Override
////    @Transactional
////    // 중간테이블 먼저 삭제 후 삭제 //0
////    public void remove(Long recruitmentId) throws IOException {
//////
//////        List<Apply> applyList = applyRepository.findByRecruitmentId(recruitmentId)
//////                .stream()
//////                .toList();
//////        // 지원 내역 + 지원 영상 S3 삭제
//////        for (int i = 0; i < applyList.size(); i++) {
//////            applyService.applyCancel(applyList.get(i).getRecruitment().getId(), applyList.get(i).getMember().getId());
//////        }
//////        // 위시리스트 삭제
//////        wishlistRepository.deleteByRecruitmentId(recruitmentId);
//////        // 공고 삭제
//////        String imageUrl = recruitmentRepository.findById(recruitmentId).orElseThrow(() -> new RecruitmentException(ExceptionCodeSet.RECRUITMENT_NOT_FOUND)).getImage();
//////        FileUtil.deleteFile(imageUrl , FolderType.RECRUIT_PATH);
//////        recruitmentRepository.deleteById(recruitmentId);
////    }
//
////    @Override
////    @Transactional //o 전체 불러오기
////    public List<RecruitmentListResponseDto> getList(Long memberId) {
////        List<Recruitment> recruitments = recruitmentRepository.findAll();
////        List<RecruitmentListResponseDto> recruitmentListResponseDtos = recruitments.stream()
////                .map(recruitment -> RecruitmentListResponseDto.builder()
////                        .id(recruitment.getId())
////                        .title(recruitment.getTitle())
////                        .image(recruitment.getImage())
////                        .endDate(recruitment.getEndDate())
////                        .wishlist(wishlistService.detail(recruitment.getId(), memberId))
////                        .build())
////                .collect(Collectors.toList());
////        return recruitmentListResponseDtos;
////    }
//
////    //"해당 멤버가 게시한 공고를 불러왔습니다."
////    @Override
////    @Transactional
////    public List<RecruitmentListResponseDto> registerList(Long memberId) {
////        List<Recruitment> recruitments = recruitmentRepository.findByMemberId(memberId);
////        List<RecruitmentListResponseDto> recruitmentListResponseDtos = recruitments.stream()
////                .map(recruitment -> RecruitmentListResponseDto.builder()
////                        .id(recruitment.getId())
////                        .title(recruitment.getTitle())
////                        .image(recruitment.getImage())
////                        .endDate(recruitment.getEndDate())
////                        .wishlist(wishlistService.detail(recruitment.getId(), memberId))
////                        .build())
////                .collect(Collectors.toList());
////        return recruitmentListResponseDtos;
////    }
//
//}