package com.example.medicalAppService.controller;


import com.example.medicalAppService.enums.Specialisation;
import com.example.medicalAppService.model.Doctor;
import com.example.medicalAppService.service.DoctorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping("/registerDoctor")
    public ResponseEntity<String> registerDoctor(@RequestBody Doctor doctorModel){
        Doctor doctor = null;
        try {
            doctor  = doctorService.registerDoctor(doctorModel);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body("Doctor registerd with id: " + doctor.getDoctorId());


    }

    @GetMapping("/getDoctorById/{doctorId}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable String doctorId){
        Doctor doctor = null;
        try {
            doctor = doctorService.getDoctorById(doctorId);
        } catch (Exception e) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(doctor);

    }

    @GetMapping("/getDoctor")
    public ResponseEntity<List<Doctor>> getDoctorBySpecialization(@RequestParam String specialization){

        List<Doctor> doctorList = new ArrayList<>();
        try {
            doctorList = doctorService.getDoctorBySpecialization(Specialisation.valueOf(specialization));
        } catch (Exception e) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ArrayList<>());
        }

        return ResponseEntity.status(HttpStatus.FOUND).body(doctorList);

    }

    @GetMapping("/getAllDoctor")
    public  ResponseEntity<List<Doctor>> getDoctorList(){

        List<Doctor> doctorList = new ArrayList<>();

        try {
            doctorList = doctorService.getDoctorList();
        }catch (Exception e){
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ArrayList<>());
        }

        return ResponseEntity.status(HttpStatus.FOUND).body(doctorList);

    }

    @PutMapping("updateAvailability/{doctorId}")
    public ResponseEntity<String> updateDoctorAvailability(@PathVariable String doctorId,
                                                           @RequestBody List<String> availabilityList){

        try {
            doctorService.updateDoctorAvailability(doctorId,availabilityList);
        } catch (Exception e) {
            ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Not updated");
        }

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Doctor's availability Updated");



    }
}
