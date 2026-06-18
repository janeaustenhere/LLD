package com.example.medicalAppService.service;

import com.example.medicalAppService.model.Patient;
import com.example.medicalAppService.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Patient registerPatient(Patient patient){
        Patient patientWithId = new Patient(patient.getFirstName(), patient.getLastName());
        return patientRepository.save(patientWithId);
    }

    public Patient getPatientById(String patientId){

        return patientRepository.getPatientById(patientId);
    }

    public List<Patient> getAllPatient(){

        return patientRepository.getAllPatient();
    }
}
