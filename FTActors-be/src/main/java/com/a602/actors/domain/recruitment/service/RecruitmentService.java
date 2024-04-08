package com.a602.actors.domain.recruitment.service;

import com.a602.actors.domain.recruitment.dto.RecruitmentListResponseDto;
import com.a602.actors.domain.recruitment.dto.RecruitmentRequestDto;
import com.a602.actors.domain.recruitment.dto.RecruitmentResponseDto;

import java.io.IOException;
import java.util.List;

public interface RecruitmentService {

    // 1.공고 등록
    public void register(RecruitmentRequestDto recruitmentRequestDto) throws IOException;
    // 2.공고 업데이트
    void update(RecruitmentRequestDto recruitmentRequestDto) throws IOException;
    // 3.공고 삭제
    void remove(Long recruitmentId) throws IOException;
    // 4.공고 전체 반환
    List<RecruitmentListResponseDto> getList(Long memberId);
    // 5.공고 상세 내역 반환
    RecruitmentResponseDto getDetail(Long recruitmentId , Long memberId);
    // 6.해당 유저가 게시한 공고 리스트
    List<RecruitmentListResponseDto> registerList(Long postMemberId);
    // 7. 공고 마감일자 변경
    void updateDate(Long recruitmentId, String endDate);
    // 8. 공고 마감일자 지난것들 변경
    void scheduleExpiredRecruitment();

}
