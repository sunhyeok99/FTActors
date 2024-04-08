package com.a602.actors.domain.apply.service;

import com.a602.actors.domain.apply.dto.ApplyDto;
import com.a602.actors.domain.apply.dto.ApplyListDto;

import java.io.IOException;
import java.util.List;

public interface ApplyService {

    // 1 . 지원
    void apply(ApplyDto applyDto) throws IOException;
    // 2. 지원취소
    void applyCancel(Long recruitmentId , Long memberId) throws IOException;
    // 3. 지원 내역 리스트
    List<ApplyListDto> myApplyList(Long memberId);
    // 4. 지원 상세 내역
    ApplyDto detail(Long applyId);

    // 5. 공고별 지원자 내역 리스트
    List<ApplyListDto> applyList(Long recruitmentId);
    
    // 6. 지원했는지 유무
    int existApply(Long recruitmentId, Long memberId);
}
