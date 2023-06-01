package com.example.bookhospitalappointments.Services;

import com.example.bookhospitalappointments.Exceptions.ApiException;
import com.example.bookhospitalappointments.Models.Patient;
import com.example.bookhospitalappointments.Repository.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import java.util.List;

@Service
@AllArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;
//    private final Appointment appointment;


    public List<Patient> getAllPatients() {
        List<Patient> patients = patientRepository.findAll();
        return patients;
    }

    public void addPatient(Patient patient, Errors errors){
        if (errors.hasErrors()){
            throw new ApiException(errors.getFieldError().getDefaultMessage());
        }
        patient.getName().toLowerCase();
        patient.getEmail().toLowerCase();
        patientRepository.save(patient);
    }

    public void updatePatient(Patient patient, Errors errors, Integer id){
        if (errors.hasErrors()){
            throw new ApiException(errors.getFieldError().getDefaultMessage());
        }

        Patient oldPatient = patientRepository.findById(id).
                orElseThrow(() -> new ApiException("Patient not found"));

        oldPatient.setName(patient.getName().toLowerCase());
        oldPatient.setPhone(patient.getPhone());
        oldPatient.setAge(patient.getAge());
        oldPatient.setEmail(patient.getEmail().toLowerCase());

        patientRepository.save(oldPatient);
    }

    public void deletePatient(Integer id){
        Patient oldPatient = patientRepository.findById(id).
                orElseThrow(() -> new ApiException("Patient not found"));
        patientRepository.delete(oldPatient);
    }
}
