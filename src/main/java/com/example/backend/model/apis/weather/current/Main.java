package com.example.backend.model.apis.weather.current;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Main {
    public Double temp;
    public Double feels_like;
    public Double temp_min;
    public Double temp_max;
    public Integer pressure;
    public Integer humidity;
}
