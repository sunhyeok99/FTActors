package com.a602.actors.domain.montage.dto;

import com.a602.actors.domain.montage.entity.Report;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

public class MontageReportDto {


    @Getter
    @Setter
    @RequiredArgsConstructor
    public static class CreateRequest{
        // 신고당한 사람 정보
        private final Long reporteeId;
        private final String reason;
        private final MultipartFile reportImage;
    }

    public static class ReportList{
        private final Long reporterId;
        private final Long reporteeId;
        private final String reason;
        private final String link;

        @Builder
        public ReportList(Long reporterId, Long reporteeId, String reason, String link){
            this.reporterId = reporterId;
            this.reporteeId = reporteeId;
            this.reason = reason;
            this.link = link;
        }

        public static MontageReportDto.ReportList toDto(Report report){
            return ReportList
                        .builder()
                        .reporterId(report.getReporter().getId())
                        .reporteeId(report.getReportee().getId())
                        .reason(report.getReason())
                        .link(report.getLink())
                        .build();
        }

    }

}
