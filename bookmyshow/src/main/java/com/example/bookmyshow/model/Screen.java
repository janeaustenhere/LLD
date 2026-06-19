package com.example.bookmyshow.model;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Screen {

    String screenId;
    Map<String,Seat> seatMap = new HashMap<>();
}
