package com.example.backend.model.apis.weather.forecast;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class WeatherForecastAPIResponse {
    public Double lat;
    public Double lon;
    public String timezone;
    public Integer timezone_offset;
    public Current current;
    public List<Minutely> minutely;
    public List<Hourly> hourly;
    public List<Daily> daily;
}
