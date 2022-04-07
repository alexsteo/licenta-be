package com.example.backend.service;

import com.example.backend.model.apis.weather.current.Coord;
import com.example.backend.model.csv.CityCsvEntry;
import com.example.backend.model.shared.Coordinate;
import com.example.backend.util.DistanceUtil;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Getter
@Setter
public class CityCsvService {

    private static List<CityCsvEntry> cityCsvEntries;

    public CityCsvService() {
    }

    public static void initializeCsvEntries() {
        List<String[]> content = new ArrayList<>();
        try {
            InputStreamReader reader = new InputStreamReader(new ClassPathResource("worldcitiespop.csv").getInputStream());
            CSVReader csvReader = new CSVReader(reader);
            content = csvReader.readAll();
        } catch (CsvException | IOException e) {
            System.out.println(e);
        }
        cityCsvEntries = content.stream().map(line -> {
            Integer lat;
            Integer lng;
            Integer pop;
            CityCsvEntry returnable = new CityCsvEntry();
            try {
                returnable.setCountry(line[0]);
                returnable.setCity(line[1]);
                returnable.setAscii(line[2]);
                returnable.setPop(line[4].equals("") ? 0.0 : Double.parseDouble(line[4]));
                returnable.setLat(Double.parseDouble(line[5]));
                returnable.setLng(Double.parseDouble(line[6]));
            } catch (NumberFormatException ignored) {
            }
            return returnable;
        }).filter(entry -> entry.getLat() != null && entry.getLat() != 0).collect(Collectors.toList());
    }

    public static List<CityCsvEntry> inBoundingBox(Double minLat, Double maxLat, Double minLng, Double maxLng) {
        return cityCsvEntries
                .stream()
                .filter(city -> city.getLat() < maxLat && city.getLat() > minLat &&
                                city.getLng() < maxLng && city.getLng() > minLng)
                .collect(Collectors.toList());
    }


    //formula is
    //     A               B
    //P1----------City------------P2
    //          C
    //to see if the city is on the line (with some room for error for cases like ring roads)
    public static List<CityCsvEntry> onALine(List<Coordinate> route, List<CityCsvEntry> inBoundingBox) {
        return inBoundingBox
                .stream()
                .filter(city -> {
                    boolean found = false;
                    for (int i = 0; i < route.size() - 1; i++) {
                        int j = 2;
                        Coordinate first = route.get(i);
                        Coordinate second = route.get(i + 1);
                        while(DistanceUtil.calculateDistanceBetweenPoints(first.getLatitude(), second.getLatitude(), first.getLongitude(), second.getLongitude()) < 10000){
                            if(i + j >= route.size()){
                                break;
                            }
                            second = route.get(i + j);
                            j++;
                        }
                        Double a = DistanceUtil.calculateDistanceBetweenPoints(first.getLatitude(), city.getLat(), first.getLongitude(), city.getLng());
                        Double b = DistanceUtil.calculateDistanceBetweenPoints(city.getLat(), second.getLatitude(), city.getLng(), second.getLongitude());
                        Double c = DistanceUtil.calculateDistanceBetweenPoints(first.getLatitude(), second.getLatitude(), first.getLongitude(), second.getLongitude());
                        if (a + b < 1.5 * c && a + b > 0.5 * c) {
                            found = true;
                            break;
                        }
                    }
                    return found;
                })
                .collect(Collectors.toList());
    }

    public List<CityCsvEntry> inBoundingBoxWithPop(Double minLat, Double maxLat, Double minLng, Double maxLng) {
        return cityCsvEntries
                .stream()
                .filter(city -> city.getPop() != 0 &&
                        city.getLat() < maxLat && city.getLat() > minLat &&
                        city.getLng() < maxLng && city.getLng() > minLng)
                .collect(Collectors.toList());
    }

    public List<CityCsvEntry> inBoundingBoxNoPop(Double minLat, Double maxLat, Double minLng, Double maxLng) {
        return cityCsvEntries
                .stream()
                .filter(city -> city.getPop() != 0 &&
                        city.getLat() < maxLat && city.getLat() > minLat &&
                        city.getLng() < maxLng && city.getLng() > minLng)
                .collect(Collectors.toList());
    }

    private List<CityCsvEntry> filterCitiesWithPop(List<CityCsvEntry> cities) {
        return cities.stream().filter(city -> city.getPop() != 0).collect(Collectors.toList());
    }

    private List<CityCsvEntry> filterCitiesNoPop(List<CityCsvEntry> cities) {
        return cities.stream().filter(city -> city.getPop() == 0).collect(Collectors.toList());
    }
}
