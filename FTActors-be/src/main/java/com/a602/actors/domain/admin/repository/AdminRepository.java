package com.a602.actors.domain.admin.repository;

import com.a602.actors.domain.montage.entity.BlackList;
import com.a602.actors.domain.montage.entity.Report;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRepository {

    List<Report> getReportList();
    List<BlackList> getBlackList();
    void acceptReport();
    void rejectReport();
}
