package com.example.medicalAppService.model;


import com.example.medicalAppService.enums.Specialisation;import lombok.Data;import javax.print.Doc;
import java.util.*;

@Data
public class Doctor {


    String doctorId;

    String firstName;

    String lastName;

    Specialisation specialisation;

    Map<String,Boolean> availabilityMap = new HashMap<>();

    double rating;

    public Doctor(String firstName, String lastName, Specialisation specialisation){

        this.doctorId = UUID.randomUUID().toString();
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialisation = specialisation;
    }


}
