package com.example.medicalAppService.model;


import lombok.Data;

@Data
public class DoctorSlot {

    String startTime;

    Doctor doctor;

    public DoctorSlot(String startTime, Doctor doctor){

        this.startTime = startTime;
        this.doctor = doctor;

    }
}
