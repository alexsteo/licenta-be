package com.example.backend.model.dto.requests.userReports;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserReportBoundingBoxRequest {

    private Double minLat;
    private Double maxLat;
    private Double minLng;
    private Double maxLng;
}
