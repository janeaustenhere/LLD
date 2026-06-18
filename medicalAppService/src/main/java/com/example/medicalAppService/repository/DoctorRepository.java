package com.example.medicalAppService.repository;


import com.example.medicalAppService.enums.Specialisation;
import com.example.medicalAppService.model.Appointment;
import com.example.medicalAppService.model.Doctor;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class DoctorRepository {

    Map<String, Doctor> doctorMap = new HashMap<>();
    Map<String,Queue<Appointment>> appointmentQueue = new HashMap<>();

    public Doctor save(Doctor doctor){

        String doctorId = doctor.getDoctorId();
        doctorMap.put(doctorId,doctor);
        return doctor;
    }

    public Doctor getDoctorById(String doctorId){

        if(!doctorMap.containsKey(doctorId)){

            System.out.println("Doctor Id not present in database");
            throw  new RuntimeException("Doctor Id not present in database");
        }else{

            return doctorMap.get(doctorId);
        }
    }

    public List<Doctor> getDoctorBySpecialization(Specialisation specialisation){

       return doctorMap.values().stream().
                filter(doctor -> doctor.getSpecialisation()
                        .equals(specialisation)).collect(Collectors.toList());
    }

    public List<Doctor> getAllDoctors(){

        return doctorMap.values().stream().toList();
    }

    public void updateDoctorAvailability(String doctorId, List<String> availabiltyList){

        Doctor doctor = doctorMap.get(doctorId);

        if(doctor == null){
            System.out.println("doctor id is not present in database : " + doctorId);
            throw new RuntimeException("doctor id is not present in database : " + doctorId);
        }

        Map<String,Boolean> availabilityMap = doctor.getAvailabilityMap();
        for(String startTime: availabiltyList){
            availabilityMap.put(startTime, true);
        }
    }

    public void updateDoctorRating(String doctorId, String patientId, double rating){

        Doctor doctor = doctorMap.get(doctorId);
        doctor.setRating(rating);

    }

    public void updateAppointmentQueue(String slotTime, Appointment appointment){
        if(appointmentQueue.containsKey(slotTime)){
            appointmentQueue.get(slotTime).add(appointment);
        }
        else {
            Queue<Appointment> queue = new LinkedList<>();
            queue.add(appointment);
            appointmentQueue.put(slotTime,queue);
        }
    }

    public Queue<Appointment> getAppointmentQueue(String slotTime){
        return appointmentQueue.get(slotTime);
    }


}
