package com.example.bookhospitalappointments.Controllers;

import com.example.bookhospitalappointments.Models.Patient;
import com.example.bookhospitalappointments.Services.PatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("api/v1/patient")
@RestController
public class PatientController {
    private final PatientService patientService;

    @GetMapping("/getAll")
    public ResponseEntity getAllPatient(){
        List<Patient> patients=patientService.getAllPatients();
        return ResponseEntity.status(200).body(patients);
    }

    @PostMapping("/add")
    public ResponseEntity addPatient(@Valid @RequestBody Patient patient , Errors errors){
        patientService.addPatient(patient,errors);
        return ResponseEntity.status(200).body("Patient added");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updatePatient(@Valid @RequestBody Patient patient, Errors errors ,@Valid @PathVariable Integer id){
        patientService.updatePatient(patient,errors,id);
        return ResponseEntity.status(200).body("Patient updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deletePatient(@PathVariable @Valid Integer id){
        patientService.deletePatient(id);
        return ResponseEntity.status(200).body("Patient deleted");
    }
}
