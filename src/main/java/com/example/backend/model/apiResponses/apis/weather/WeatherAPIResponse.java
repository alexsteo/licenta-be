package com.example.backend.model.apiResponses.apis.weather;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class WeatherAPIResponse {
    public Double lat;
    public Double lon;
    public String timezone;
    public Integer timezone_offset;
    public Current current;
    public List<Minutely> minutely;
    public List<Hourly> hourly;
    public List<Daily> daily;
}
