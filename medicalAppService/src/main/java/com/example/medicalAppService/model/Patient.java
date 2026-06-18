package com.example.medicalAppService.model;


import lombok.Data;

import java.util.UUID;

@Data
public class Patient {

    String  patientId;

    String firstName;

    String lastName;

    public Patient (String firstName, String lastName){
        this.patientId = UUID.randomUUID().toString();
        this.firstName = firstName;
        this.lastName = lastName;
    }


}
