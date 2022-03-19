package com.example.backend.model.apis.route;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Leg{
    public Boolean hasTollRoad;
    public Boolean hasBridge;
    public String destNarrative;
    public Double distance;
    public Boolean hasTimedRestriction;
    public Boolean hasTunnel;
    public Boolean hasHighway;
    public Integer index;
    public String formattedTime;
    public Integer origIndex;
    public Boolean hasAccessRestriction;
    public Boolean hasSeasonalClosure;
    public Boolean hasCountryCross;
    public List<List<Object>> roadGradeStrategy;
    public Integer destIndex;
    public Integer time;
    public Boolean hasUnpaved;
    public String origNarrative;
    public List<Maneuver> maneuvers;
    public Boolean hasFerry;
}
