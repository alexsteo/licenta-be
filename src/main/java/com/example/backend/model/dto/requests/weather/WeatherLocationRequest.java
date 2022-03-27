package com.example.backend.model.dto.requests.weather;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class WeatherLocationRequest {
    private Double lat;
    private Double lng;
}
