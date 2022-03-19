package com.example.backend.model.apis.route;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Options{
    public List<Double> arteryWeights;
    public Integer cyclingRoadFactor;
    public Integer timeType;
    public Boolean useTraffic;
    public Boolean returnLinkDirections;
    public Boolean countryBoundaryDisplay;
    public Boolean enhancedNarrative;
    public String locale;
    public List<Object> tryAvoidLinkIds;
    public Integer drivingStyle;
    public Boolean doReverseGeocode;
    public Integer generalize;
    public List<Object> mustAvoidLinkIds;
    public Boolean sideOfStreetDisplay;
    public String routeType;
    public Boolean avoidTimedConditions;
    public Integer routeNumber;
    public String shapeFormat;
    public Integer maxWalkingDistance;
    public Boolean destinationManeuverDisplay;
    public Integer transferPenalty;
    public String narrativeType;
    public Integer walkingSpeed;
    public Integer urbanAvoidFactor;
    public Boolean stateBoundaryDisplay;
    public String unit;
    public Integer highwayEfficiency;
    public Integer maxLinkId;
    public Integer maneuverPenalty;
    public List<Object> avoidTripIds;
    public Integer filterZoneFactor;
    public String manmaps;
}
