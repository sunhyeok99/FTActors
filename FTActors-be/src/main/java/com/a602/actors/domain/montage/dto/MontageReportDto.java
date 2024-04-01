package com.a602.actors.domain.montage.dto;

import com.a602.actors.domain.montage.entity.Report;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.beans.ConstructorProperties;

public class MontageReportDto {


    @Getter
    @Setter
    public static class CreateReport{
        // 신고당한 사람 정보
        private final String reason;

        @ConstructorProperties(value = {"reason"})
        public CreateReport(String reason) {
            this.reason = reason;
        }
    }

    @Getter
    @Setter
    public static class ReportList{ // reportId 파악
        private final Long reportId;
        private final String reporterStageName;
        private final String reporteeStageName;
        private final String reason;
        private final String link;

        @QueryProjection
        @Builder
        public ReportList(Long reportId, String reporterStageName, String reporteeStageName, String reason, String link){
            this.reportId = reportId;
            this.reporterStageName = reporterStageName;
            this.reporteeStageName = reporteeStageName;
            this.reason = reason;
            this.link = link;
        }

//        public static MontageReportDto.ReportList toDto(Report report){
//            return ReportList
//                        .builder()
//                        .reporterId(report.getReporter().getId())
//                        .reporteeId(report.getReportee().getId())
//                        .reason(report.getReason())
//                        .link(report.getLink())
//                        .build();
//        }

    }

}
