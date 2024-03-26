package com.a602.actors.domain.admin.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReportResultDto {
    private Long reportId;

    public ReportResultDto(){

    }

    public ReportResultDto(Long reportId){
        this.reportId = reportId;
    }
}
