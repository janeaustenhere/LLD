package com.example.uber.strategy;

import com.example.uber.model.EstimatedFareAndTime;
import com.example.uber.model.Product;
import com.example.uber.model.Ride;

import java.util.List;
import java.util.Map;

public interface PriceStartegy {

    Map<String, EstimatedFareAndTime> calculateFare(Ride ride, List<Product> productList);
}
