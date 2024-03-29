package com.example.backend.model.dto.responses.my;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UserReportForBoundingBoxResponse {

    private List<UserReportResponse> reports;
}
