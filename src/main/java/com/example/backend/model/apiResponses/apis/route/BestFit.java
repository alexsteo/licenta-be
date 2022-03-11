package com.example.backend.model.apiResponses.apis.route;

import lombok.ToString;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BestFit{
    public Integer margin;
    public Integer newLevel;
    public Integer width;
    public Integer scale;
    public Integer newScale;
    public NewCenter newCenter;
    public Integer height;
}
