package com.example.uber.repository;

import com.example.uber.model.Fare;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public class FareRepository {

    public Map<String, Fare> fareMap = new HashMap<>();

    public Fare save(Fare fare){
        String fareId = UUID.randomUUID().toString();
        fare.setFareId(fareId);
        fareMap.put(fareId,fare);
        return fare;
    }

    public Fare getFareById(String fareId){
        return fareMap.get(fareId);
    }

    public List<Fare> getFareList(){
        return fareMap.values().stream().toList();
    }


}
