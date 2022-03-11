package com.example.backend.model.apiResponses.apis.weather;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Temp{
    public Double day;
    public Double min;
    public Double max;
    public Double night;
    public Double eve;
    public Double morn;
}
