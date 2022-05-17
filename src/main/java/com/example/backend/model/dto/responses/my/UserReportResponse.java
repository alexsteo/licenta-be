package com.example.backend.model.dto.responses.my;

import com.example.backend.model.shared.UserReportType;
import lombok.*;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Builder
public class UserReportResponse {
    private Double lat;
    private Double lng;
    private Map<UserReportType, Integer> typeAndAmount;

    public void increment(UserReportType type) {
        if(this.typeAndAmount.containsKey(type)) {
            this.typeAndAmount.put(type, this.typeAndAmount.get(type) + 1);
        } else {
            this.typeAndAmount.put(type, 1);
        }
    }

    public UserReportType getType() {
        for (Map.Entry<UserReportType, Integer> entry : this.typeAndAmount.entrySet()) {
            if(entry.getValue() > 0) {
                return entry.getKey();
            }
        }
        return null;
    }
}
