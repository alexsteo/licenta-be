package com.example.backend.util;

import com.example.backend.model.shared.Coordinate;

public class DistanceUtil {

    /**
     * Uses Haversine formula to calculate the shortest distance between two points given by their coordinates
     *
     * @param lat1 latitude of first point
     * @param lat2 latitude of second point
     * @param lng1 longitude of first point
     * @param lng2 longitude of second point
     * @return the resulting distance in meters
     */
    public static Double calculateDistanceBetweenPoints(Double lat1, Double lat2, Double lng1, Double lng2) {
        Double earthRadius = 6371 * 1000.0;
        Double phi1 = lat1 * Math.PI / 180;
        Double phi2 = lat2 * Math.PI / 180;
        Double dPhi = Math.abs(lat2 - lat1) * Math.PI / 180;
        Double dLambda = Math.abs(lng2 - lng1) * Math.PI / 180;
        Double a = Math.sin(dPhi / 2) * Math.sin(dPhi / 2) + Math.cos(phi1) * Math.cos(phi2) * Math.sin(dLambda / 2) * Math.sin(dLambda / 2);
        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return earthRadius * c;
    }

    public static Double closerTo(Coordinate o1, Coordinate o2, Coordinate to) {
        return DistanceUtil.calculateDistanceBetweenPoints(o1.getLatitude(), to.getLatitude(), o1.getLongitude(), to.getLongitude()) -
                DistanceUtil.calculateDistanceBetweenPoints(o2.getLatitude(), to.getLatitude(), o2.getLongitude(), to.getLongitude());
    }
}
