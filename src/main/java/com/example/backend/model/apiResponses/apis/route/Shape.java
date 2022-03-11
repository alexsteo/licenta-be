package com.example.backend.model.apiResponses.apis.route;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Shape{
    public List<Integer> legIndexes;
    public List<Integer> maneuverIndexes;
    public List<Double> shapePoints;
}
