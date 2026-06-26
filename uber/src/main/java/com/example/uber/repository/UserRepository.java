package com.example.uber.repository;

import com.example.uber.enums.AccountStatus;
import com.example.uber.model.Rider;
import com.example.uber.model.UserInput;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public class UserRepository {

    Map<String, Rider> riderMap = new HashMap<>();

    public Rider save(Rider rider){

        riderMap.put(rider.getUserId(),rider);

        return rider;
    }

    public Rider getRiderById(String riderId){
        return riderMap.get(riderId);
    }

    public List<Rider> getAllRiderList(){
        return riderMap.values().stream().toList();
    }
}
