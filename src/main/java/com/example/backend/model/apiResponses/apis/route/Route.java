package com.example.backend.model.apiResponses.apis.route;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Route {
    public BoundingBox boundingBox;
    public Double distance;
    public Boolean hasTimedRestriction;
    public List<Object> computedWaypoints;
    public RouteError routeError;
    public String realTimeAlternatePercentSavingsThreshhold;
    public String formattedTime;
    public BestFit bestFit;
    public String realTimeAlternateTimeSavingsThreshhold;
    public Boolean hasAccessRestriction;
    public Boolean hasSeasonalClosure;
    public Boolean hasCountryCross;
    public List<Leg> legs;
    public Options options;
    public Boolean hasFerry;
    public Boolean hasTollRoad;
    public Boolean hasBridge;
    public Shape shape;
    public Boolean hasTunnel;
    public Boolean hasHighway;
    public String sessionId;
    public String maxRoutes;
    public Integer realTime;
    public Double fuelUsed;
    public String name;
    public Integer timeOverage;
    public List<Location> locations;
    public Integer time;
    public Boolean hasUnpaved;
    public List<Integer> locationSequence;
    public MapState mapState;
    public List<AlternateRoute> alternateRoutes;
}
