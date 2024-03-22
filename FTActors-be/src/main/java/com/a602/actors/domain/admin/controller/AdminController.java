package com.a602.actors.domain.admin.controller;


import com.a602.actors.global.common.dto.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @GetMapping("/report/list")
    public ApiResponse<String> getReportList(){


        return new ApiResponse<>(200, "", "");
    }

}
