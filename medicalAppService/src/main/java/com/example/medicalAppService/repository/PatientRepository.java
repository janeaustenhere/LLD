package com.example.medicalAppService.repository;

import com.example.medicalAppService.model.Patient;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class PatientRepository {

    Map<String, Patient> patientMap = new HashMap<>();

    public Patient save(Patient patient){

        String patientId = patient.getPatientId();
        patientMap.put(patientId, patient);
        return patient;
    }

    public Patient getPatientById(String patientId){
        if(!patientMap.containsKey(patientId)){
            System.out.println("Patient id not present in database. PatientId = " + patientId);
            throw  new RuntimeException(" Patient id not present in database");
        }
        return patientMap.get(patientId);
    }

    public List<Patient> getAllPatient(){

        return patientMap.values().stream().toList();
    }
}

