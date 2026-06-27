package com.example.uber.strategy;

import com.example.uber.model.EstimatedFareAndTime;
import com.example.uber.model.Product;
import com.example.uber.model.Ride;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component("demandBasedPriceStrategy")
public class DemandBasedPriceStrategy implements PriceStartegy{
    @Override
    public Map<String, EstimatedFareAndTime> calculateFare(Ride ride, List<Product> productList) {
        return null;
    }
}
