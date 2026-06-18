package com.example.medicalAppService.strategy;

import com.example.medicalAppService.model.Doctor;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Component("sortDoctorListBasedOnSlotTime")
public class SortDoctorListBasedOnSlotTime implements DoctorListSortStrategy{
    @Override
    public List<Doctor> sortDoctorList(List<Doctor> doctorList) {
        return doctorList.stream()
                .sorted(Comparator.comparing(this::getEarliestAvailableSlot)).toList();
    }

    private LocalTime getEarliestAvailableSlot(Doctor doctor){

        return  doctor.getAvailabilityMap().entrySet()
                .stream().filter(Map.Entry:: getValue)
                .map(entry -> LocalTime.parse(entry.getKey()))
                .min(LocalTime::compareTo)
                .orElse(LocalTime.MAX);
    }
}
