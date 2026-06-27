package com.example.uber.service;

import com.example.uber.model.EstimatedFareAndTime;
import com.example.uber.model.Fare;
import com.example.uber.model.Product;
import com.example.uber.model.Ride;
import com.example.uber.repository.FareRepository;
import com.example.uber.strategy.PriceStartegy;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class FareCalculationService {

    private final FareRepository fareRepository;
    private final PriceStartegy priceStartegy;

    @PostConstruct
    public void init(){
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(this::removeExpiredFare,3,3, TimeUnit.MINUTES);
    }

    private void removeExpiredFare(){
        fareRepository.fareMap.entrySet()
                .removeIf( stringFareEntry -> stringFareEntry
                        .getValue().getExpireTime() < System.currentTimeMillis());

    }


    public FareCalculationService(FareRepository fareRepository,
                                  @Qualifier("timeBasedPriceStrategy") PriceStartegy priceStartegy) {
        this.fareRepository = fareRepository;
        this.priceStartegy = priceStartegy;
    }

    public Map<String, EstimatedFareAndTime> calculateEstimatedFareForRide(Ride ride, List<Product> productList){
        return priceStartegy.calculateFare(ride,productList);
    }

    public Fare createFareObjectForRide(Ride ride){

        Fare fare = Fare.builder()
                .sourceLocation(ride.getPickUpLocation())
                .fareId(UUID.randomUUID().toString())
                .destinationLocation(ride.getDropLocation())
                .productName(ride.getProductName())
                .amount(ride.getAmount())
                .expireTime(System.currentTimeMillis() + Duration.ofMinutes(3).toMillis())
                .build();

        fareRepository.save(fare);
        return fare;


    }
}
