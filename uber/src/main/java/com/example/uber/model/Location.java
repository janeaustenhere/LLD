package com.example.uber.model;

import lombok.Data;

@Data
public class Location {


    double longitude;
    double latitude;

    public double getDistance(Location otherLocation){
        double longitudeDiff =  Math.abs(this.longitude - otherLocation.longitude);
        double latitudeDiff = Math.abs(this.latitude - otherLocation.latitude);
        double diff =  Math.abs(longitudeDiff* longitudeDiff - latitudeDiff*latitudeDiff);
        return Math.sqrt(diff);
    }
}
