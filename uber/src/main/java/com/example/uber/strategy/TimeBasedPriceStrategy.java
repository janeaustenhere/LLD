package com.example.uber.strategy;

import com.example.uber.model.EstimatedFareAndTime;
import com.example.uber.model.Product;
import com.example.uber.model.Ride;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component("timeBasedPriceStrategy")
public class TimeBasedPriceStrategy implements PriceStartegy{
    @Override
    public Map<String,EstimatedFareAndTime> calculateFare(Ride ride, List<Product> productList) {

        double distance = getEstimatedDistance(ride);
        long time = getEstimatedTime(ride);

        return productList.stream()
                .collect(Collectors.toMap(Product::getName, product -> {
                    return EstimatedFareAndTime.builder().fare(product.getBaseFare()
                            + product.getFarePerKM() * distance
                            + product.getFarePerHour() * time).estimatedTime(time).build();
                }));
    }

    public double getEstimatedDistance(Ride ride){
        double distance = ride.getPickUpLocation().getDistance(ride.getDropLocation());
        ride.setEstimatedDistance(distance);
        return distance;
    }

    public long getEstimatedTime(Ride ride){
        long minutes =  Duration.ofMinutes((long) Math.ceil(ride.getEstimatedDistance()*60)).toMinutes();
        ride.setEstimatedTime(minutes);
        return minutes;

    }
}
