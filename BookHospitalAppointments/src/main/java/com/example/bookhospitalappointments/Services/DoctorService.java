package com.example.bookhospitalappointments.Services;

import com.example.bookhospitalappointments.DTOs.DoctorDto;
import com.example.bookhospitalappointments.Exceptions.ApiException;
import com.example.bookhospitalappointments.Models.Appointment;
import com.example.bookhospitalappointments.Models.Doctor;
import com.example.bookhospitalappointments.Models.Hospital;
import com.example.bookhospitalappointments.Repository.AppointmentRepository;
import com.example.bookhospitalappointments.Repository.DoctorRepository;
import com.example.bookhospitalappointments.Repository.HospitalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final HospitalRepository hospitalRepository;
    private final AppointmentRepository appointmentRepository;

    public List<Doctor> doctors(){
        return doctorRepository.findAll();
    }

    public void add(DoctorDto dto, Errors errors){
        if(errors.hasErrors())
            throw new ApiException(errors.getFieldError().getDefaultMessage());

        Hospital hospital = hospitalRepository.findById(dto.getHospital_id())
                .orElseThrow(()-> new ApiException("Hospitals id not found"));

        Doctor doctor = new Doctor(dto.getName(), dto.getClinic(), dto.getRank(), hospital);

        doctorRepository.save(doctor);
    }

    public void update(int id, DoctorDto dto, Errors errors){
        if(errors.hasErrors())
            throw new ApiException(errors.getFieldError().getDefaultMessage());

        Hospital hospital = hospitalRepository.findById(dto.getHospital_id())
                .orElseThrow(()-> new ApiException("hospital id not found"));

        Doctor findDoctor = doctorRepository.findById(id)
                .orElseThrow(()-> new ApiException("doctor id not found"));

        findDoctor.setName(dto.getName());
        findDoctor.setClinic(dto.getClinic());
        findDoctor.setRank(dto.getRank());
        findDoctor.setHospital(hospital);

        doctorRepository.save(findDoctor);
    }

    public void delete(int id){
        Doctor findDoctor = doctorRepository.findById(id).orElseThrow(()-> new ApiException("id not found"));
        doctorRepository.delete(findDoctor);
    }

    public List<Appointment> getAppointments(String doctorName, String status){
        List<Appointment> appointments =  appointmentRepository.findByStatus(status);
        ArrayList<Appointment> allStatus = new ArrayList<>();
        for(int i = 0; i < appointments.size(); i++) {
            if (appointments.get(i).getDoctor().getName().equals(doctorName)) {
                if (appointments.get(i).getStatus().equals("pending"))
                    allStatus.add(appointments.get(i));
            }
        }
        return allStatus;
    }

    public void newStatus(String doctorName, String patientName, String status){
        Appointment appointment =  appointmentRepository.findByDoctorAndPatient(doctorName, patientName);

        if(appointment == null)
            throw new ApiException("this patient not have appointment");

        else
            appointment.setStatus(status);

        appointmentRepository.save(appointment);
    }
}
