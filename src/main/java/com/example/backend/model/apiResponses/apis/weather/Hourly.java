package com.example.backend.model.apiResponses.apis.weather;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Hourly{
    public Integer dt;
    public Double temp;
    public Double feels_like;
    public Integer pressure;
    public Integer humidity;
    public Double dew_point;
    public Double uvi;
    public Integer clouds;
    public Integer visibility;
    public Double wind_speed;
    public Integer wind_deg;
    public Double wind_gust;
    public List<Weather> weather;
    public Double pop;
    public Snow snow;
}
