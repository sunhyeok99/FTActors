package com.a602.actors.domain.admin.controller;


import com.a602.actors.domain.admin.dto.BlackListDto;
import com.a602.actors.domain.admin.service.AdminService;
import com.a602.actors.domain.montage.dto.MontageReportDto;
import com.a602.actors.global.common.dto.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    public final AdminService adminService;

    public AdminController(AdminService adminService){
        this.adminService = adminService;
    }

    @GetMapping("/report/list")
    public ApiResponse<List<MontageReportDto.ReportList>> getReportList(){
        return new ApiResponse<>(200, "신고 리스트를 불러왔습니다.", adminService.getReportList());
    }
    @GetMapping("/black-list")
    public ApiResponse<List<BlackListDto.BlackListSet>> getBlackList(){
        return new ApiResponse<>(200, "블랙 리스트를 불러왔습니다.", adminService.getBlackList());
    }

}
