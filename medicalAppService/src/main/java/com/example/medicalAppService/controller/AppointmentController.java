package com.example.medicalAppService.controller;


import com.example.medicalAppService.model.Appointment;
import com.example.medicalAppService.service.AppointmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    private final AppointmentService appointmentService;


    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping("/bookAppointment")
    public ResponseEntity<Appointment> bookAppointment(@RequestBody Appointment appointment){
        Appointment bookedAppointment = null;
        try {
            bookedAppointment = appointmentService.bookAppointment(appointment);

        } catch (Exception e) {
            ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(null);
        }

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(bookedAppointment);


    }

    @GetMapping("/getAllAppointment")
    public ResponseEntity<List<Appointment>> getAllAppointment(){
        List<Appointment> allBookedAppointment = null;
        try {
            allBookedAppointment = appointmentService.getAllBookedAppointment();
        } catch (Exception e) {
           ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

       return  ResponseEntity.status(HttpStatus.FOUND).body(allBookedAppointment);
    }

    @PostMapping("/cancelAppointment/{appointmentId}")
    public ResponseEntity<String> cancelAppointment(@PathVariable  String appointmentId){
        try {
            appointmentService.cancelAppointment(appointmentId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.status(HttpStatus.OK).body("Appointment Cancelled");


    }
}
