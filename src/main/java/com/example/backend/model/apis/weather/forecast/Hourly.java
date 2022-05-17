package com.example.backend.model.apis.weather.forecast;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
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
    public Rain rain;
}
