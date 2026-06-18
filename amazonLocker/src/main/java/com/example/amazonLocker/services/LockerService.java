package com.example.amazonLocker.services;


import com.example.amazonLocker.models.Locker;
import com.example.amazonLocker.repositories.LockerRepository;
import org.springframework.stereotype.Service;

@Service
public class LockerService {


    private final LockerRepository lockerRepository;

    public LockerService(LockerRepository lockerRepository) {
        this.lockerRepository = lockerRepository;
    }

     public void registerLocker(String zipCode, Locker locker){
         lockerRepository.addLockerToList(zipCode,locker);
     }


}
