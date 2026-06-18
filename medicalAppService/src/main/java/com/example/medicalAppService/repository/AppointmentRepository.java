package com.example.medicalAppService.repository;


import com.example.medicalAppService.model.Appointment;
import com.example.medicalAppService.model.Doctor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class AppointmentRepository {

    Map<String,Appointment> appointmentMap = new HashMap<>();



    public Appointment save(Appointment bookingAppointment){

        String bookingId = bookingAppointment.getBookingId();
        appointmentMap.put(bookingId,bookingAppointment);
        return bookingAppointment;
    }

    public void cancelAppointment(String appointmentId){


        appointmentMap.remove(appointmentId);
    }

    public Appointment getAppointmentById(String appointmentId){

        Appointment appointment = appointmentMap.get(appointmentId);
        if(appointment == null){
            System.out.println(" No appointment present with this id: -" + appointmentId);
            throw new RuntimeException("No appointment present with this id: " + appointmentId);
        }

        return appointment;
    }

    public List<Appointment> getAppointmentByDoctorId(String doctorId){

        List<Appointment> doctorAppointmentList = appointmentMap.values().stream()
                .filter(appointment -> appointment.getDoctorId().equals(doctorId))
                .collect(Collectors.toUnmodifiableList());


        return doctorAppointmentList;
    }

    public List<Appointment> getAppointmentByPatientId(String patientId){
        List<Appointment> patientAppointmentList = appointmentMap.values().stream()
                .filter(appointment -> appointment.getDoctorId().equals(patientId))
                .collect(Collectors.toUnmodifiableList());


        return patientAppointmentList;

    }

    public List<Appointment> getAllBookedAppointment(){
        List<Appointment> bookedAppointments = appointmentMap.values().stream().toList();
        return bookedAppointments;
    }


}
