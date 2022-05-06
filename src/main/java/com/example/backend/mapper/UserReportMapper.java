package com.example.backend.mapper;

import com.example.backend.model.db.UserReport;
import com.example.backend.model.dto.responses.my.UserReportResponse;
import com.example.backend.model.shared.UserReportType;

import java.util.HashMap;
import java.util.Map;

public class UserReportMapper {

    public static UserReportResponse convertUserReport(UserReport report) {
        Map<UserReportType, Integer> typeAndAmount = new HashMap<>();
        typeAndAmount.put(report.getType(), 1);
        return UserReportResponse.builder()
                .typeAndAmount(typeAndAmount)
                .lat(report.getLat())
                .lng(report.getLng())
                .build();
    }
}
