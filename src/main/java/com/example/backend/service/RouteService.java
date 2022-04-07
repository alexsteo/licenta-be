package com.example.backend.service;

import com.example.backend.api.RouteAPI;
import com.example.backend.mapper.WeatherDataMapper;
import com.example.backend.model.apis.route.AlternateRoute;
import com.example.backend.model.apis.route.RouteAPIResponse;
import com.example.backend.model.csv.CityCsvEntry;
import com.example.backend.model.db.WeatherData;
import com.example.backend.model.dto.requests.route.RouteRequest;
import com.example.backend.model.dto.responses.my.LocationWithWeatherDaily;
import com.example.backend.model.dto.responses.route.RouteResponse;
import com.example.backend.model.shared.Coordinate;
import com.example.backend.util.DistanceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RouteService {

    @Autowired
    private RouteAPI routeAPI;

    @Autowired
    private CityCsvService cityCsvService;

    @Autowired
    private WeatherDataService weatherDataService;

    public static final int KM_TO_M = 1000;

    private static final Double THRESHOLD = 0.3;

    public RouteResponse getRoute(RouteRequest request) {
        RouteResponse routeResponse = new RouteResponse();
        List<LocationWithWeatherDaily> weatherData = new ArrayList<>();
        RouteAPIResponse apiResponse = routeAPI.getDirections(request);
        List<List<CityCsvEntry>> citiesOnRoutes = new ArrayList<>();

        addRoutesToResponse(apiResponse, routeResponse);

        // get cities on each route and make a list of those lists
        Set<CityCsvEntry> set = new HashSet<>();
        for (Pair<Double, List<Coordinate>> route : routeResponse.getRoutesWithLength()) {
            findCitiesOnRoute(route, citiesOnRoutes);
        }

        // combine the lists using a set to exclude dupes
        for (List<CityCsvEntry> onARoute : citiesOnRoutes) {
            set.addAll(onARoute);
        }
        List<CityCsvEntry> allCitiesOnRoutes = new ArrayList<>(set);

        //filter cities that have no population in the CSV, as they are usually small cities, and should not make a difference
        allCitiesOnRoutes = allCitiesOnRoutes.stream().filter(cityCsvEntry -> cityCsvEntry.getPop() != 0).collect(Collectors.toList());

        //split the values into a map, containing as a key which routes they are on, and a list of the cities on that route as the value
        Map<String, List<CityCsvEntry>> routeCombinations = new HashMap<>();
        for (CityCsvEntry entry : allCitiesOnRoutes) {
            assignCityToRouteCombination(entry, routeCombinations, citiesOnRoutes);
        }

        //calculate which cities for each route should have the weather checked
        List<CityCsvEntry> checkpoints = new ArrayList<>();
        for (Pair<Double, List<Coordinate>> routeWithLength : routeResponse.getRoutesWithLength()) {
            calculateRouteCheckpointsForWeatherCheck(routeWithLength, allCitiesOnRoutes, checkpoints);
        }

        //filter duplicates as two routes may have common roads which may result in checkpoints appearing in both
        checkpoints = new ArrayList<>(new HashSet<>(checkpoints));

        //check if certain route combinations are not represented and add the biggest city to the checkpoints
        for(List<CityCsvEntry> entries : routeCombinations.values()) {
            addCheckpointsForUnrepresentedRouteCombinations(entries, checkpoints);
        }

        //check the db for weather data about other cities, and add it to the response if found
        for(CityCsvEntry cityOnRoute: allCitiesOnRoutes) {
            addDataForCitiesInDb(checkpoints, cityOnRoute, weatherData);
        }

        //get weather data for the current checkpoints
        for(CityCsvEntry checkpoint: checkpoints) {
            getWeatherDataForCity(checkpoint, weatherData);
        }

        routeResponse.setWeatherData(weatherData);
        return routeResponse;
    }

    private void addRoutesToResponse(RouteAPIResponse apiResponse, RouteResponse routeResponse) {
        routeResponse.addRoute(apiResponse.getRoute().getDistance(), createResponseFromShapePoints(apiResponse.getRoute().getShape().getShapePoints()));
        if (apiResponse.getRoute().getAlternateRoutes() != null) {
            for (AlternateRoute alternateRoute : apiResponse.getRoute().getAlternateRoutes()) {
                routeResponse.addRoute(alternateRoute.getRoute().getDistance(), createResponseFromShapePoints(alternateRoute.getRoute().getShape().getShapePoints()));
            }
        }
    }

    private void findCitiesOnRoute(Pair<Double, List<Coordinate>> route, List<List<CityCsvEntry>> citiesOnRoutes) {
        Double minLat = getMinLatitudeInList(route.getSecond());
        Double maxLat = getMaxLatitudeInList(route.getSecond());
        Double maxLng = Collections.max(route.getSecond(), (left, right) -> (int) (left.getLongitude() - right.getLongitude())).getLongitude();
        Double minLng = Collections.min(route.getSecond(), (left, right) -> (int) (left.getLongitude() - right.getLongitude())).getLongitude();
        List<CityCsvEntry> inBoundingBox = CityCsvService.inBoundingBox(minLat, maxLat, minLng, maxLng);
        List<CityCsvEntry> onARoute = CityCsvService.onALine(route.getSecond(), inBoundingBox);
        citiesOnRoutes.add(onARoute);
    }

    private void assignCityToRouteCombination(CityCsvEntry entry, Map<String, List<CityCsvEntry>> routeCombinations, List<List<CityCsvEntry>> citiesOnRoutes) {
        String inRoutes = "";
        for (int i = 0; i < citiesOnRoutes.size(); i++) {
            List<CityCsvEntry> entries = citiesOnRoutes.get(i);
            if (entries.contains(entry)) {
                inRoutes = inRoutes.concat(String.valueOf(i));
            }
        }
        List<CityCsvEntry> value = routeCombinations.get(inRoutes);
        if (value == null) {
            value = new ArrayList<>();
        }
        value.add(entry);
        routeCombinations.put(inRoutes, value);
    }

    private void calculateRouteCheckpointsForWeatherCheck(Pair<Double, List<Coordinate>> routeWithLength, List<CityCsvEntry> allCitiesOnRoutes, List<CityCsvEntry> checkpoints) {
        double totalDistance = routeWithLength.getFirst();
        double distanceTraveled = 0.0;
        int checkpoint = 1;
        List<Coordinate> route = routeWithLength.getSecond();
        List<CityCsvEntry> checkpointsInRoute = new ArrayList<>();
        for (int i = 0; i < route.size() - 4; i += 4) {
            List<Coordinate> points = route.subList(i, i + 4);
            double distance = DistanceUtil.calculateDistanceBetweenPoints(points.get(0).getLatitude(), points.get(3).getLatitude(), points.get(0).getLongitude(), points.get(3).getLongitude()) / 1000;
            distanceTraveled += distance;
            if (distanceTraveled > totalDistance / 8 * checkpoint) {
                CityCsvEntry biggest = new CityCsvEntry();
                for (Coordinate point : points) {
                    CityCsvEntry city = allCitiesOnRoutes.stream()
                            .filter(loc -> DistanceUtil.calculateDistanceBetweenPoints(loc.getLat(), point.getLatitude(), loc.getLng(), point.getLongitude()) < THRESHOLD * totalDistance / 8 * KM_TO_M)
                            .min((o1, o2) -> Double.valueOf(o2.getPop() - o1.getPop()).intValue()).orElse(new CityCsvEntry());
                    if (city.getPop() != null && (biggest.getPop() == null || city.getPop() > biggest.getPop())) {
                        biggest = city;
                    }
                }
                checkpointsInRoute.add(biggest);
                checkpoint++;
                if (checkpoint == 9) {
                    break;
                }
            }
        }
        checkpoints.addAll(checkpointsInRoute);
    }

    private void addCheckpointsForUnrepresentedRouteCombinations(List<CityCsvEntry> entries, List<CityCsvEntry> checkpoints) {
        boolean found = false;
        for(CityCsvEntry entry : entries) {
            if(checkpoints.contains(entry)) {
                found = true;
                break;
            }
        }
        if(!found) {
            CityCsvEntry biggest = entries.stream().max((o1, o2) -> (int) (o1.getPop() - o2.getPop())).orElse(new CityCsvEntry());
            checkpoints.add(biggest);
        }
    }

    private void addDataForCitiesInDb(List<CityCsvEntry> checkpoints, CityCsvEntry cityOnRoute, List<LocationWithWeatherDaily> weatherData) {
        if(!checkpoints.contains(cityOnRoute)) {
            List<WeatherData> data = weatherDataService.getWeatherOrDeleteIfOld(cityOnRoute);
            if(data.size() > 0) {
                LocationWithWeatherDaily daily = new LocationWithWeatherDaily();
                daily.setData(data.stream().map(WeatherDataMapper::convertWeatherData).collect(Collectors.toList()));
                weatherData.add(daily);
            }
        }
    }

    private void getWeatherDataForCity(CityCsvEntry city, List<LocationWithWeatherDaily> weatherData) {
        List<WeatherData> data = weatherDataService.getWeatherOrDeleteAndGetIfOld(city);
        LocationWithWeatherDaily daily = new LocationWithWeatherDaily();
        daily.setData(data.stream().map(WeatherDataMapper::convertWeatherData).collect(Collectors.toList()));
        weatherData.add(daily);
    }

    //This and next method are here because Collections.max is broken
    private Double getMinLatitudeInList(List<Coordinate> route) {
        Double min = 180.0;
        for (Coordinate c : route) {
            if (c.getLatitude() < min) {
                min = c.getLatitude();
            }
        }
        return min;
    }

    private Double getMaxLatitudeInList(List<Coordinate> route) {
        Double max = -180.0;
        for (Coordinate c : route) {
            if (c.getLatitude() > max) {
                max = c.getLatitude();
            }
        }
        return max;
    }

    private List<Coordinate> createResponseFromShapePoints(List<Double> shapePoints) {
        List<Coordinate> returnable = new ArrayList<>();
        for (int i = 0; i < shapePoints.size(); i += 2) {
            returnable.add(new Coordinate(shapePoints.get(i), shapePoints.get(i + 1)));
        }
        return returnable;
    }
}
