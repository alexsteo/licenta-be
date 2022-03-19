package com.example.backend.model.apis.weather.forecast;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Temp{
    public Double day;
    public Double min;
    public Double max;
    public Double night;
    public Double eve;
    public Double morn;
}
