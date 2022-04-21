package com.example.backend.mapper;

import com.example.backend.model.db.UserReport;
import com.example.backend.model.dto.responses.my.UserReportResponse;

public class UserReportMapper {

    public static UserReportResponse convertUserReport(UserReport report) {
        return UserReportResponse.builder()
                .userReportType(report.getType())
                .lat(report.getLat())
                .lng(report.getLng())
                .amount(1)
                .build();
    }
}
