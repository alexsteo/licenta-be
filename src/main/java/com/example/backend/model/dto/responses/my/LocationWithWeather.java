package com.example.backend.model.dto.responses.my;

import lombok.*;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Setter
public class LocationWithWeather {

    private Integer plusHours;
    private Double temperature;
    private Integer clouds;
    private Double windSpeed;
    private Double rain;
    private Double snow;
    private String weatherDescription;
    private String location;
    private Double lat;
    private Double lng;
}
