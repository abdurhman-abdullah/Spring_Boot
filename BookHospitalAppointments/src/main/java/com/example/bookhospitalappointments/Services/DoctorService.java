package com.example.bookhospitalappointments.Services;

import com.example.bookhospitalappointments.Exceptions.ApiException;
import com.example.bookhospitalappointments.Models.Doctor;
import com.example.bookhospitalappointments.Repository.DoctorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import java.util.List;

@Service
@AllArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public List<Doctor> doctors(){
        return doctorRepository.findAll();
    }

    public void add(Doctor doctor, Errors errors){
        if(errors.hasErrors())
            throw new ApiException(errors.getFieldError().getDefaultMessage());

        doctorRepository.save(doctor);
    }

    public void update(int id, Doctor doctor, Errors errors){
        if(errors.hasErrors())
            throw new ApiException(errors.getFieldError().getDefaultMessage());

        Doctor findDoctor = doctorRepository.findById(id).orElseThrow(()-> new ApiException("id not found"));

        findDoctor.setName(doctor.getName());
        findDoctor.setClinic(doctor.getClinic());
        findDoctor.setRank(doctor.getRank());

        doctorRepository.save(findDoctor);
    }

    public void delete(int id){
        Doctor findDoctor = doctorRepository.findById(id).orElseThrow(()-> new ApiException("id not found"));
        doctorRepository.delete(findDoctor);
    }

}
