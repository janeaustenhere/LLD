package com.example.medicalAppService.strategy;

import com.example.medicalAppService.model.Doctor;

import java.util.List;

public interface DoctorListSortStrategy {

    List<Doctor> sortDoctorList(List<Doctor> doctorList);
}
