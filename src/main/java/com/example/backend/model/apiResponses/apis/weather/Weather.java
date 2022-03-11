package com.example.backend.model.apiResponses.apis.weather;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Weather{
    public Integer id;
    public String main;
    public String description;
    public String icon;
}
