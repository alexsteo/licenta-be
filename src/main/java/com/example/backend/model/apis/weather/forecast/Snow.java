package com.example.backend.model.apis.weather.forecast;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Snow{
    @JsonProperty("1h")
    public double _1h;
}
