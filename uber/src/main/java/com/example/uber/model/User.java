package com.example.uber.model;

import com.example.uber.enums.UserType;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public abstract class User {

    String userId;
    String userName;
    Double rating;
    String email;
    String contactNumber;


    abstract UserType getUserType();

}
