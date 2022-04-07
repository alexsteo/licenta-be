package com.example.backend.model.dto.responses.my;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class LocationWithWeather {

    private Timestamp timestamp;
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
