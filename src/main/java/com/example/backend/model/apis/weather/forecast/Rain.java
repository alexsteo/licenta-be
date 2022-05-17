package com.example.backend.model.apis.weather.forecast;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class Rain {
    @JsonProperty("1h")
    public Double _1h;
}
