package com.a602.actors.domain.apply.service;

import com.a602.actors.domain.apply.dto.ApplyDto;
import com.a602.actors.domain.apply.entity.Apply;

import java.util.List;

public interface ApplyService {

    // 1 . 지원
    void apply(ApplyDto applyDto);
    // 2. 지원취소
    void applyCancel(Long recruitmentId , Long memberId);
    // 3. 지원 내역 리스트
    List<ApplyDto> myApplyList(Long memberId);
    // 4. 지원 상세 내역
    Apply detail(Long applyId);

    // 5. 공고별 지원자 내역 리스트
    List<ApplyDto> applyList(Long recruitmentId);
}
