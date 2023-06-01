package com.example.bookhospitalappointments.Services;

import com.example.bookhospitalappointments.DTOs.AppointmentDto;
import com.example.bookhospitalappointments.Exceptions.ApiException;
import com.example.bookhospitalappointments.Models.Appointment;
import com.example.bookhospitalappointments.Models.Doctor;
import com.example.bookhospitalappointments.Models.Patient;
import com.example.bookhospitalappointments.Repository.AppointmentRepository;
import com.example.bookhospitalappointments.Repository.DoctorRepository;
import com.example.bookhospitalappointments.Repository.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import java.util.List;

@Service
@AllArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;


    public List<Appointment> getAllAppointments() {
        List<Appointment> appointments = appointmentRepository.findAll();
        return appointments;
    }

    public void addAppointment(AppointmentDto dto, Errors errors){
        if (errors.hasErrors()){
            throw new ApiException(errors.getFieldError().getDefaultMessage());
        }
        Patient patient = patientRepository.findById(dto.getPatient_id())
                .orElseThrow(()-> new ApiException("patient id not found"));

        Doctor doctor = doctorRepository.findById(dto.getDoctor_id())
                .orElseThrow(()-> new ApiException("doctor id not found"));

        Appointment appointment = new Appointment(dto.getBookingDate(), "pending", patient, doctor);

        appointmentRepository.save(appointment);
    }

    public void updateAppointment(AppointmentDto dto, Errors errors, Integer id){

        if (errors.hasErrors()){
            throw new ApiException(errors.getFieldError().getDefaultMessage());
        }

        Appointment oldAppointment = appointmentRepository.findById(id)
                .orElseThrow(()-> new ApiException("id not found"));

        Patient patient = patientRepository.findById(dto.getPatient_id())
                .orElseThrow(()-> new ApiException("patient id not found"));

        Doctor doctor = doctorRepository.findById(dto.getDoctor_id())
                .orElseThrow(()-> new ApiException("doctor id not found"));


        oldAppointment.setBookingDate(dto.getBookingDate());
        oldAppointment.setPatient(patient);
        oldAppointment.setDoctor(doctor);

        appointmentRepository.save(oldAppointment);
    }

    public void deleteAppointment(Integer id){
        Appointment oldAppointment = appointmentRepository.findById(id)
                .orElseThrow(()-> new ApiException("id not found"));
        appointmentRepository.delete(oldAppointment);
    }

    //    endpoint that take Appointment id and return the Patient name for that class
    public  String getAppointmentPatient(Integer id){
        Appointment appointment= appointmentRepository.findAppointmentById(id);
        if(appointment==null){
            throw new ApiException("Appointment not found");
        }
        if(appointment.getPatient()==null){
            throw new ApiException("Appointment for this patient not found");
        }
        return appointment.getPatient().getName();
    }

    //    endpoint that take Appointment id and return the Doctor name for that class
    public  String getAppointmentDoctor(Integer id){
        Appointment appointment= appointmentRepository.findAppointmentById(id);
        if(appointment==null){
            throw new ApiException("Appointment not found");
        }
        if(appointment.getDoctor()==null){
            throw new ApiException("Appointment for this doctor not found");
        }
        return appointment.getDoctor().getName();
    }




}
