package com.example.uber.model;


import com.example.uber.enums.AccountStatus;
import com.example.uber.enums.UserType;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class Rider extends User {

    int totalNUmberOfRideTaken;
    AccountStatus accountStatus;

    @Override
    UserType getUserType() {
        return UserType.RIDER;
    }
}
