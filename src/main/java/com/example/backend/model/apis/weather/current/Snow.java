package com.example.backend.model.apis.weather.current;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Snow {
    @JsonProperty("1h")
    public Double _1h;
}
