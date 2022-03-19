package com.example.backend.model.apis.weather.forecast;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Daily{
    public Integer dt;
    public Integer sunrise;
    public Integer sunset;
    public Integer moonrise;
    public Integer moonset;
    public Double moon_phase;
    public Temp temp;
    public FeelsLike feels_like;
    public Integer pressure;
    public Integer humidity;
    public Double dew_point;
    public Double wind_speed;
    public Integer wind_deg;
    public Double wind_gust;
    public List<Weather> weather;
    public Integer clouds;
    public Double pop;
    public Double uvi;
    public Double snow;
}
