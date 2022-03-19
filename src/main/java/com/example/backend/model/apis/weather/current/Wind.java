package com.example.backend.model.apis.weather.current;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Wind {
    public Double speed;
    public Integer deg;
}
