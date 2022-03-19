package com.example.backend.model.apis.weather.current;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Sys {
    public Integer type;
    public Integer id;
    public Double message;
    public String country;
    public Integer sunrise;
    public Integer sunset;
}
