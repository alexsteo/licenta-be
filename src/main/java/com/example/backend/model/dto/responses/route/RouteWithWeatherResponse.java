package com.example.backend.model.dto.responses.route;

import com.example.backend.model.dto.responses.my.LocationWithWeather;
import com.example.backend.model.dto.responses.my.LocationWithWeatherHourly;
import com.example.backend.model.dto.responses.my.UserReportForBoundingBoxResponse;
import com.example.backend.model.shared.Coordinate;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
@Setter
public class RouteWithWeatherResponse {

    private List<RouteWithData> routeWithData;
    private List<LocationWithWeatherHourly> weatherData;
    private UserReportForBoundingBoxResponse userReports;

    public RouteWithWeatherResponse() {
        initializeArrays();
    }

    public RouteWithWeatherResponse(Double routeLength, List<Coordinate> coordinateList) {
        initializeArrays();
        this.addRoute(routeLength, coordinateList);
    }

    public void addRoute(Double routeLength, List<Coordinate> newRoute) {
        routeWithData.add(new RouteWithData(newRoute, routeLength));
    }

    public void addWeatherData(List<LocationWithWeather> weatherDataEntry) {
        for(LocationWithWeather entry: weatherDataEntry){
            for(LocationWithWeatherHourly hourly : weatherData) {
                if(hourly.getPlusHours().equals(entry.getPlusHours())){
                    hourly.addWeatherLocation(entry);
                    break;
                }
            }
        }
    }

    public void setRouteScore(int routeIndex, Double score) {
        this.routeWithData.get(routeIndex).setScore(score);
    }

    private void initializeArrays() {
        this.weatherData = new ArrayList<>();
        this.routeWithData = new ArrayList<>();
        for(int i = 0; i < 24; i++) {
            weatherData.add(new LocationWithWeatherHourly(i));
        }
    }
}
