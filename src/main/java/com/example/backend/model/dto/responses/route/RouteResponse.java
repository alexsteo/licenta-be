package com.example.backend.model.dto.responses.route;

import com.example.backend.model.db.WeatherData;
import com.example.backend.model.dto.responses.my.LocationWithWeather;
import com.example.backend.model.dto.responses.my.LocationWithWeatherDaily;
import com.example.backend.model.shared.Coordinate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.util.Pair;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class RouteResponse {

    private List<Pair<Double, List<Coordinate>>> routesWithLength;
    private List<LocationWithWeatherDaily> weatherData;

    public RouteResponse() {
        this.routesWithLength = new ArrayList<>();
    }

    public RouteResponse(Double routeLength, List<Coordinate> coordinateList) {
        this.routesWithLength = new ArrayList<>();
        this.routesWithLength.add(Pair.of(routeLength, coordinateList));
    }

    public void addRoute(Double routeLength, List<Coordinate> newRoute) {
        routesWithLength.add(Pair.of(routeLength, newRoute));
    }
}
