package com.example.medicalAppService.service;

import com.example.medicalAppService.model.Appointment;
import com.example.medicalAppService.model.Doctor;
import com.example.medicalAppService.repository.AppointmentRepository;
import com.example.medicalAppService.repository.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;


    public AppointmentService(AppointmentRepository appointmentRepository, DoctorRepository doctorRepository) {
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
    }

    public void cancelAppointment(String appointmentId){
        Appointment appointmentToCancel = appointmentRepository.getAppointmentById(appointmentId);
        String doctorId = appointmentToCancel.getDoctorId();
        Doctor doctor = doctorRepository.getDoctorById(doctorId);
        String slotTIme = appointmentToCancel.getSlotTime();
        doctor.getAvailabilityMap().put(slotTIme, true);
        if(doctorRepository.getAppointmentQueue(slotTIme) == null || doctorRepository.getAppointmentQueue(slotTIme).size() == 0){
            doctor.getAvailabilityMap().put(slotTIme, true);
        }else{
            Appointment appointmentToadd = null;
            while(appointmentToadd == null  && doctorRepository.getAppointmentQueue(slotTIme).size() > 0) {
                appointmentToadd = doctorRepository.getAppointmentQueue(slotTIme).poll();
                this.bookAppointment(appointmentToadd);
            }

        }

        appointmentRepository.cancelAppointment(appointmentId);

    }

    public Appointment bookAppointment(Appointment appointment) {
        String patientId = appointment.getPatientId();
        Appointment anotherAppointmentForPatient = appointmentRepository.getAppointmentByPatientId(patientId).stream()
                .filter(appointment1 -> appointment1.getSlotTime().equals(appointment.getSlotTime())).findAny().get();
        if(anotherAppointmentForPatient != null){
            System.out.println("Another appointment is booked for patient :" + patientId);
            return null;
        }
        String doctorId = appointment.getDoctorId();
        Doctor doctor = doctorRepository.getDoctorById(doctorId);
        boolean isAvailable = doctor.getAvailabilityMap().get(appointment.getSlotTime());
        Appointment bookedAppointment = new Appointment(appointment.getDoctorId(), appointment.getPatientId(), appointment.getSlotTime());
        if (isAvailable){

        bookedAppointment = appointmentRepository.save(bookedAppointment);
        doctor.getAvailabilityMap().put(appointment.getSlotTime(), false);
      }else {
            doctorRepository.updateAppointmentQueue(appointment.getSlotTime(),appointment);

        }
        return bookedAppointment;
    }

    public List<Appointment> getDoctorAppointmentList(String doctorId){

        List<Appointment> doctorAppointmentList = new ArrayList<>();
        doctorAppointmentList = appointmentRepository.getAppointmentByDoctorId(doctorId);
        return doctorAppointmentList;
    }

    public List<Appointment> getPatientAppointList(String patientId){

        List<Appointment> patientAppointmentList = new ArrayList<>();
        patientAppointmentList = appointmentRepository.getAppointmentByPatientId(patientId);
        return patientAppointmentList;

    }

    public List<Appointment> getAllBookedAppointment(){

        List<Appointment> allBookedAppointment = new ArrayList<>();
        allBookedAppointment = appointmentRepository.getAllBookedAppointment();
        return allBookedAppointment;
    }


}
