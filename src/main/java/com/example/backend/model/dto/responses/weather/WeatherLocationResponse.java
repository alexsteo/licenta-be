package com.example.backend.model.dto.responses.weather;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class WeatherLocationResponse {
    private Double temperature;
    private String name;
    private Double lat;
    private Double lng;
}
