package com.example.backend.model.apiResponses.apis.route;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Location{
    public boolean dragPoint;
    public DisplayLatLng displayLatLng;
    public String adminArea4;
    public String adminArea5;
    public String postalCode;
    public String adminArea1;
    public String adminArea3;
    public String type;
    public String sideOfStreet;
    public String geocodeQualityCode;
    public String adminArea4Type;
    public Integer linkId;
    public String street;
    public String adminArea5Type;
    public String geocodeQuality;
    public String adminArea1Type;
    public String adminArea3Type;
    public LatLng latLng;
}
