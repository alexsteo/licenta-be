package com.example.backend.model.apis.route;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Maneuver{
    public Double distance;
    public List<String> streets;
    public String narrative;
    public Integer turnType;
    public StartPoint startPoint;
    public Integer index;
    public String formattedTime;
    public String directionName;
    public List<Object> maneuverNotes;
    public List<Integer> linkIds;
    public List<Object> signs;
    public String mapUrl;
    public String transportMode;
    public Integer attributes;
    public Integer time;
    public String iconUrl;
    public Integer direction;
}
