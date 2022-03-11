package com.example.backend.model.apiResponses.apis.weather;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Current{
    public Integer dt;
    public Integer sunrise;
    public Integer sunset;
    public Double temp;
    public Double feels_like;
    public Integer pressure;
    public Integer humidity;
    public Double dew_point;
    public Integer uvi;
    public Integer clouds;
    public Integer visibility;
    public Double wind_speed;
    public Integer wind_deg;
    public List<Weather> weather;
}
