package com.a602.actors.domain.admin.repository;

import com.a602.actors.domain.admin.dto.BlackListDto;
import com.a602.actors.domain.montage.dto.MontageReportDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRepository {

    List<MontageReportDto.ReportList> getReportList();
    List<BlackListDto.BlackListSet> getBlackList();

    void acceptReport(Long reportId) throws IllegalArgumentException;
    void rejectReport(Long reportId) throws IllegalArgumentException;
}
