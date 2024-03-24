package com.a602.actors.domain.admin.repository;

import com.a602.actors.domain.admin.dto.BlackListDto;
import com.a602.actors.domain.montage.entity.Report;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRepository {

    List<Report> getReportList();
    List<BlackListDto.BlackListSet> getBlackList();
    void acceptReport();
    void rejectReport();
}
