package com.example.backend.model.apis.weather.forecast;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Minutely{
    public Integer dt;
    public Integer precipitation;
}
