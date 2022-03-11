package com.example.backend.model.apiResponses.apis.weather;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FeelsLike{
    public Double day;
    public Double night;
    public Double eve;
    public Double morn;
}
