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
        return this.city.hashCode();
    }
}
