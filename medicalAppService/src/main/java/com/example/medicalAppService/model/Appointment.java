package com.example.medicalAppService.model;

import lombok.Data;

import java.util.UUID;

@Data
public class Appointment {

    String bookingId;

    String doctorId;

    String patientId;

    String slotTime;

    public Appointment(String doctorId, String patientId,
                       String slotTime){

        this.bookingId = UUID.randomUUID().toString();
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.slotTime = slotTime;

    }
}
