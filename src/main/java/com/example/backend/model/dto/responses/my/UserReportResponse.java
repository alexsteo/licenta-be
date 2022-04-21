package com.example.backend.model.dto.responses.my;

import com.example.backend.model.shared.UserReportType;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UserReportResponse {
    private UserReportType userReportType;
    private Double lat;
    private Double lng;
    private Integer amount;

    public void increment() {
        this.amount++;
    }
}
