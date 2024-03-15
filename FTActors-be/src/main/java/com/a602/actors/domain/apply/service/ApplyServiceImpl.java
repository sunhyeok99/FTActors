package com.a602.actors.domain.apply.service;

import com.a602.actors.domain.apply.dto.ApplyDto;
import com.a602.actors.domain.apply.entity.Apply;
import com.a602.actors.domain.apply.repository.ApplyRepository;
import com.a602.actors.domain.member.repository.MemberRepository;
import com.a602.actors.domain.recruitment.repository.RecruitmentRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ApplyServiceImpl implements ApplyService {

    private final ApplyRepository applyRepository;
    private final MemberRepository memberRepository;
    private final RecruitmentRespository recruitmentRespository;


    @Override
    @Transactional
    public void apply(ApplyDto applyDto) {

    }

    @Override
    @Transactional
    public void applyCancel(Long recruitmentId, Long memberId) {

    }

    @Override
    public List<ApplyDto> myApplyList(Long memberId) {
        return null;
    }

    @Override
    public Apply detail(Long applyId) {
        return null;
    }

    @Override
    public List<ApplyDto> applyList(Long recruitmentId) {
        return null;
    }
}
