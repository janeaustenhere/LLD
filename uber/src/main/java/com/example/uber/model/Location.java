package com.example.uber.model;

import lombok.Data;

@Data
public class Location {


    double longitude;
    double latitude;

    double getDistance(Location otherLocation){
        double longitudeDiff =  Math.abs(this.longitude - otherLocation.longitude);
        double latitudeDiff = Math.abs(this.latitude - otherLocation.latitude);

        return Math.sqrt(longitudeDiff* longitudeDiff - latitudeDiff*latitudeDiff);
    }
}
