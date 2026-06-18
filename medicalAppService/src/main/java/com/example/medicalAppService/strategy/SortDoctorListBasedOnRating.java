package com.example.medicalAppService.strategy;

import com.example.medicalAppService.model.Doctor;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Component("sortDoctorListBasedOnRating")
public class SortDoctorListBasedOnRating implements DoctorListSortStrategy {
    @Override
    public List<Doctor> sortDoctorList(List<Doctor> doctorList) {
        return doctorList.stream().sorted(Comparator.comparing(Doctor::getRating).reversed()).toList();
    }


}
