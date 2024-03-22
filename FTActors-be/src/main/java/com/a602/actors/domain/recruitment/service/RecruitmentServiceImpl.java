package com.a602.actors.domain.recruitment.service;

import com.a602.actors.domain.apply.entity.Apply;
import com.a602.actors.domain.apply.repository.ApplyRepository;
import com.a602.actors.domain.apply.service.ApplyService;
import com.a602.actors.domain.member.repository.MemberRepository;
import com.a602.actors.domain.recruitment.crawling.Crawling;
import com.a602.actors.domain.recruitment.dto.RecruitmentListResponseDto;
import com.a602.actors.domain.recruitment.dto.RecruitmentRequestDto;
import com.a602.actors.domain.recruitment.dto.RecruitmentResponseDto;
import com.a602.actors.domain.recruitment.entity.Recruitment;
import com.a602.actors.domain.recruitment.repository.RecruitmentRepository;
import com.a602.actors.domain.recruitment.repository.WishlistRepository;
import com.a602.actors.global.common.config.FileUtil;
import com.a602.actors.global.common.enums.FolderType;
import com.a602.actors.global.exception.ExceptionCodeSet;
import com.a602.actors.global.exception.MemberException;
import com.a602.actors.global.exception.RecruitmentException;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class RecruitmentServiceImpl implements RecruitmentService {

    private final RecruitmentRepository recruitmentRepository;
    private final ApplyRepository applyRepository;
    private final ApplyService applyService;
    private final MemberRepository memberRepository;
    private final WishlistRepository wishlistRepository;
    private final WishlistService wishlistService;

    @Override
    @Transactional
    public void register(RecruitmentRequestDto recruitmentRequestDto) throws IOException {
        String imageUrl = FileUtil.uploadFile(recruitmentRequestDto.getImage(), FolderType.RECRUIT_PATH);

        Recruitment recruitment = Recruitment.builder()
                .title(recruitmentRequestDto.getTitle())
                .content(recruitmentRequestDto.getContent())
                .member(memberRepository.findById(recruitmentRequestDto.getPostMemberId()).orElseThrow(() -> new MemberException(ExceptionCodeSet.MEMBER_NOT_FOUND)))
                .category(recruitmentRequestDto.getCategory())
                .image(imageUrl)
                .startDate(recruitmentRequestDto.getStartDate())
                .endDate(recruitmentRequestDto.getEndDate())
                .build();
            recruitmentRepository.save(recruitment);
    }

    @Override
    @Transactional
    public void update(RecruitmentRequestDto recruitmentRequestDto) throws IOException {
        Recruitment recruitment = recruitmentRepository.findById(recruitmentRequestDto.getId()).orElseThrow(() -> new RecruitmentException(ExceptionCodeSet.RECRUITMENT_NOT_FOUND));
       // S3에서 이미지 먼저 삭제 후 다시 들어온 이미지로 교체
        String imageurl = recruitment.getImage();
        if(recruitmentRequestDto.getImage() != null){
        FileUtil.deleteFile(recruitment.getImage() , FolderType.RECRUIT_PATH);
        imageurl = FileUtil.uploadFile(recruitmentRequestDto.getImage() , FolderType.RECRUIT_PATH);
        }
        recruitment.updateRecruitment(recruitmentRequestDto.getTitle(), recruitmentRequestDto.getContent(),
                recruitmentRequestDto.getCategory(), imageurl,
                recruitmentRequestDto.getStartDate(), recruitmentRequestDto.getEndDate());
        // 업데이트 완료
    }

    @Override
    @Transactional
    public void updateDate(Long recruitmentId, String endDate) {
        Recruitment recruitment = recruitmentRepository.findById(recruitmentId).orElseThrow(() -> new RecruitmentException(ExceptionCodeSet.RECRUITMENT_NOT_FOUND));
        recruitment.updateEndDate(endDate);
        // 종료날자 업데이트 완료
    }


    @Override
    @Transactional
    // 중간테이블 먼저 삭제 후 삭제
    public void remove(Long recruitmentId) throws IOException {

        List<Apply> applyList = applyRepository.findByRecruitmentId(recruitmentId)
                .stream()
                .toList();
        // 지원 내역 + 지원 영상 S3 삭제
        for (int i = 0; i < applyList.size(); i++) {
            applyService.applyCancel(applyList.get(i).getRecruitment().getId(), applyList.get(i).getMember().getId());
        }
        // 위시리스트 삭제
        wishlistRepository.deleteByRecruitmentId(recruitmentId);
        // 공고 삭제
        String imageUrl = recruitmentRepository.findById(recruitmentId).orElseThrow(() -> new RecruitmentException(ExceptionCodeSet.RECRUITMENT_NOT_FOUND)).getImage();
        FileUtil.deleteFile(imageUrl , FolderType.RECRUIT_PATH);
        recruitmentRepository.deleteById(recruitmentId);
    }

    @Override
    @Transactional
    public List<RecruitmentListResponseDto> getList(Long memberId) {
        List<Recruitment> recruitments = recruitmentRepository.findAllRecruitment();
        List<RecruitmentListResponseDto> recruitmentListResponseDtos = recruitments.stream()
                .map(recruitment -> RecruitmentListResponseDto.builder()
                        .id(recruitment.getId())
                        .title(recruitment.getTitle())
                        .image(recruitment.getImage())
                        .endDate(recruitment.getEndDate())
                        .wishlist(wishlistService.detail(recruitment.getId(), memberId))
                        .privateRecruitment(recruitment.getPrivateRecruitment())
                        .build())
                .collect(Collectors.toList());
        return recruitmentListResponseDtos;
    }

    @Override
    @Transactional
    public RecruitmentResponseDto getDetail(Long recruitmentId, Long memberId) {
        Recruitment recruitment = recruitmentRepository.findById(recruitmentId).orElseThrow(() -> new RecruitmentException(ExceptionCodeSet.RECRUITMENT_NOT_FOUND));
        RecruitmentResponseDto recruitmentResponseDto = RecruitmentResponseDto.builder()
                .id(recruitment.getId())
                .title(recruitment.getTitle())
                .content(recruitment.getContent())
                .postMemberName(recruitment.getMember().getMemberId())
                .postMemberId(recruitment.getMember().getId())
                .category(recruitment.getCategory())
                .image(recruitment.getImage())
                .startDate(recruitment.getStartDate())
                .endDate(recruitment.getEndDate())
                .wishlist(wishlistService.detail(recruitmentId, memberId))
                .apply(applyService.existApply(recruitmentId , memberId))
                .privateRecruitment(recruitment.getPrivateRecruitment())
                .build();
        return recruitmentResponseDto;
    }

    @Override
    @Transactional
    public List<RecruitmentListResponseDto> registerList(Long memberId) {
        // 여기서는 마감된 공고도 같이 긁어옴
        List<Recruitment> recruitments = recruitmentRepository.findByMemberId(memberId);
        List<RecruitmentListResponseDto> recruitmentListResponseDtos = recruitments.stream()
                .map(recruitment -> RecruitmentListResponseDto.builder()
                        .id(recruitment.getId())
                        .title(recruitment.getTitle())
                        .image(recruitment.getImage())
                        .endDate(recruitment.getEndDate())
                        .wishlist(wishlistService.detail(recruitment.getId(), memberId))
                        .privateRecruitment(recruitment.getPrivateRecruitment())
                        .build())
                .collect(Collectors.toList());
        return recruitmentListResponseDtos;
    }
    // 마감일자 지난 리스트 불러 다 T로 바꿈
    @Override
    @Scheduled(cron = "0 0 0 * * *") // 초 분 시 일 월 요일
    @Transactional
    public void scheduleExpiredRecruitment() {
        LocalDate currentTime = LocalDate.now();
        String currentDate = currentTime.toString();
        List<Recruitment> list = recruitmentRepository.findByActivatedRecruitment(currentDate);
        for (Recruitment recruitment : list) {
            System.out.println(recruitment.getTitle());
            recruitment.updatePrivate();
        }
        Crawling crawling = new Crawling();
        List<Recruitment> recruitmentList = crawling.getRecruitmentDatas();
    }

}
