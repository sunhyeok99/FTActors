package com.a602.actors.domain.admin.controller;


import com.a602.actors.domain.admin.dto.BlackListDto;
import com.a602.actors.domain.admin.dto.ReportResultDto;
import com.a602.actors.domain.admin.service.AdminService;
import com.a602.actors.domain.montage.dto.MontageReportDto;
import com.a602.actors.global.common.dto.ApiResponse;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/report/acceptance")
    public ApiResponse<String> acceptReport(@RequestBody ReportResultDto resultDto){
        return new ApiResponse<>(200, "신고 접수를 허용합니다.", adminService.acceptReport(resultDto.getReportId()));
    }

    @PostMapping("/report/rejectance")
    public ApiResponse<String> rejectReport(@RequestBody ReportResultDto resultDto){
        return new ApiResponse<>(200, "신고 접수를 불허합니다.", adminService.rejectReport(resultDto.getReportId()));
    }

}
