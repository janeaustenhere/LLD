package com.example.bookmyshow.model;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Theatre {

    String theaterId;
    String theaterName;
    Map<String,Screen> screenMap = new HashMap<>();
    Address address;
}
