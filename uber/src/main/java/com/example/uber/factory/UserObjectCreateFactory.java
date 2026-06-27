package com.example.uber.factory;

import com.example.uber.enums.AccountStatus;
import com.example.uber.enums.UserType;
import com.example.uber.model.Driver;
import com.example.uber.model.Rider;
import com.example.uber.model.User;
import com.example.uber.model.UserInput;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
public class UserObjectCreateFactory {

    public Object createUserObject (UserInput userInput){

        switch(userInput.getUserType()){

            case RIDER -> {
                return Rider.builder().totalNUmberOfRideTaken(0)
                        .accountStatus(AccountStatus.ACTIVE)
                        .userId(UUID.randomUUID().toString())
                        .contactNumber(userInput.getContactNumber())
                        .email(userInput.getEmail())
                        .userName(userInput.getUserName())
                        .rating(null).build();
            }

            case DRIVER -> {
                return Driver.builder()
                        .userId(UUID.randomUUID().toString())
                        .contactNumber(userInput.getContactNumber())
                        .email(userInput.getEmail())
                        .userName(userInput.getUserName())
                        .vehicle(userInput.getVehicle())
                        .isAvailable(new AtomicBoolean(true))
                        .currentLocation(userInput.getDriverLocation())
                        .rating(null).build();
            }

        }


        return null;
    }


}
