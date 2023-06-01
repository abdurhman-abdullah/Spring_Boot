package com.example.bookhospitalappointments.Services;

import com.example.bookhospitalappointments.Exceptions.ApiException;
import com.example.bookhospitalappointments.Models.Hospital;
import com.example.bookhospitalappointments.Repository.HospitalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import java.util.List;
@Service
@AllArgsConstructor
public class HospitalService {
    private final HospitalRepository hospitalRepository;
    public List<Hospital> getAll() {
        return hospitalRepository.findAll();
    }

    public void add(Hospital hospital, Errors errors) {
        if (errors.hasErrors())
            throw new ApiException(errors.getFieldError().getDefaultMessage());

        hospitalRepository.save(hospital);


    }


    public void update(Integer id, Hospital hospital, Errors errors){
        if(errors.hasErrors())
            throw new ApiException(errors.getFieldError().getDefaultMessage());

        Hospital findHospital = hospitalRepository.findById(id).
                orElseThrow(() -> new ApiException("id not found"));

        findHospital.setName(hospital.getName());
        findHospital.setCity(hospital.getCity());
        findHospital.setLocation(hospital.getLocation());
        hospitalRepository.save(findHospital);
    }

    public void delete(Integer id){
        Hospital findHospital = hospitalRepository.findById(id).
                orElseThrow(() -> new ApiException("id not found"));
        hospitalRepository.delete(findHospital);
    }
}
