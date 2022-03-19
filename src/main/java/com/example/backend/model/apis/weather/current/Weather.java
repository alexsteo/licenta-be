package com.example.backend.model.apis.weather.current;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Weather {
    public Integer id;
    public String main;
    public String description;
    public String icon;
}
