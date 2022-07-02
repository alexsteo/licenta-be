package com.example.backend.model.apis.weather.current;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class WeatherCurrentAPIResponse {
    public Coord coord;
    public List<Weather> weather;
    public String base;
    public Main main;
    public Integer visibility;
    public Wind wind;
    public Clouds clouds;
    public Integer dt;
    public Sys sys;
    public Integer timezone;
    public Integer id;
    public String name;
    public Integer cod;
    public Snow snow;
    public Rain rain;
}
