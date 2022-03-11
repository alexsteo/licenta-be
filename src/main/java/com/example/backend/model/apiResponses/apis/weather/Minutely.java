package com.example.backend.model.apiResponses.apis.weather;

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
