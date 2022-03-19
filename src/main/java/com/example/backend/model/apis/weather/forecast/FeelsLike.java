package com.example.backend.model.apis.weather.forecast;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FeelsLike{
    public Double day;
    public Double night;
    public Double eve;
    public Double morn;
}
