package com.example.medicalAppService.service;


import com.example.medicalAppService.enums.Specialisation;
import com.example.medicalAppService.model.Doctor;
import com.example.medicalAppService.repository.DoctorRepository;
import com.example.medicalAppService.strategy.DoctorListSortStrategy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;


    private final DoctorListSortStrategy doctorListSortStrategy;


    public DoctorService(DoctorRepository doctorRepository,
                         @Qualifier("sortDoctorListBasedOnSlotTime") DoctorListSortStrategy doctorListSortStrategy) {
        this.doctorRepository = doctorRepository;
        this.doctorListSortStrategy = doctorListSortStrategy;
    }

    public Doctor registerDoctor(Doctor doctor){
        Doctor doctorWithId = new Doctor(doctor.getFirstName(), doctor.getLastName(),
                doctor.getSpecialisation());
       return doctorRepository.save(doctorWithId);
    }

    public Doctor getDoctorById(String doctorId){

        return doctorRepository.getDoctorById(doctorId);
    }

    public List<Doctor> getDoctorBySpecialization(Specialisation specialisation){

        List<Doctor> doctorList = doctorRepository.getDoctorBySpecialization(specialisation);
        doctorList = doctorListSortStrategy.sortDoctorList(doctorList);
        return doctorList;

    }

    public List<Doctor> getDoctorList(){

        List<Doctor> doctorList = doctorRepository.getAllDoctors();
        doctorList = doctorListSortStrategy.sortDoctorList(doctorList);
        return doctorList;
    }

    public void updateDoctorAvailability(String doctorId, List<String> availabilityList){

        doctorRepository.updateDoctorAvailability(doctorId,availabilityList);

    }

    public void updateDoctorRating(String doctorId, String patientId, double rating){

        doctorRepository.updateDoctorRating(doctorId,patientId,rating);
    }
}
