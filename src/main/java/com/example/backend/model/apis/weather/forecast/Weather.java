package com.example.backend.model.apis.weather.forecast;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class Weather{
    public Integer id;
    public String main;
    public String description;
    public String icon;
}
