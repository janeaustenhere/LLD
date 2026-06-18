package com.example.medicalAppService.controller;


import com.example.medicalAppService.model.Patient;
import com.example.medicalAppService.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping("/registerPatient")
    public ResponseEntity<String> registerPatient(@RequestBody Patient patientModel){
        Patient patient;
        try {
            patient = patientService.registerPatient(patientModel);
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Patient not created in System");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body("Patient Register in System : " + patient.getPatientId());

    }

    @GetMapping("/getAllPatient")
    public ResponseEntity<List<Patient>> getAllPatient(){
        List<Patient> patientList = new ArrayList<>();
        try {
            patientList =   patientService.getAllPatient();
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ArrayList<>());
        }

        return ResponseEntity.status(HttpStatus.FOUND).body(patientList);

    }

    @GetMapping("/getPatientById/{patientId}")
    public ResponseEntity<Patient> getPatient(@PathVariable String patientId){
        Patient patient = null;
        try {
            patient =   patientService.getPatientById(patientId);
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.status(HttpStatus.FOUND).body(patient);


    }





}
