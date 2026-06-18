package com.example.amazonLocker.repositories;

import com.example.amazonLocker.models.Locker;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class LockerRepository {

    final private Map<String, List<Locker>> zipcodeToLockerMap;


    public LockerRepository(Map<String, List<Locker>> zipcodeToLockerMap) {
        this.zipcodeToLockerMap = zipcodeToLockerMap;

    }

    public void addLockerToList(String zipcode, Locker locker) {
        zipcodeToLockerMap.computeIfAbsent(zipcode,
                list -> new ArrayList<>()).add(locker);
    }
}
