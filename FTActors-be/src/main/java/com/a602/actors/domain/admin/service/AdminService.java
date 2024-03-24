package com.a602.actors.domain.admin.service;


import com.a602.actors.domain.admin.dto.BlackListDto;
import com.a602.actors.domain.admin.repository.AdminRepository;
import com.a602.actors.domain.montage.dto.MontageReportDto;
import com.a602.actors.domain.montage.entity.BlackList;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    private final AdminRepository adminRepository;
    public AdminService(AdminRepository adminRepository){
        this.adminRepository = adminRepository;
    }

    public List<MontageReportDto.ReportList> getReportList(){
        return adminRepository.getReportList().stream().map(MontageReportDto.ReportList::toDto).toList();
    }

    public List<BlackListDto.BlackListSet> getBlackList(){
        return adminRepository.getBlackList();
    }

}
