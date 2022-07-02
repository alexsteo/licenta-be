package com.example.backend.model.csv;

import com.example.backend.service.CityCsvService;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class CityCsvEntry {
    private String country;
    private String city;
    private String ascii;
    private Double pop;
    private Double lat;
    private Double lng;

    @Override
    public int hashCode() {
        if(this.city != null) {
            return this.city.hashCode();
        } else {
            return 1;
        }
    }
}
