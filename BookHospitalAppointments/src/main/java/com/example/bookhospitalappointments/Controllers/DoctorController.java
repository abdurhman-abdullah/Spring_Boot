package com.example.bookhospitalappointments.Controllers;

import com.example.bookhospitalappointments.DTOs.DoctorDto;
import com.example.bookhospitalappointments.Models.Appointment;
import com.example.bookhospitalappointments.Models.Doctor;
import com.example.bookhospitalappointments.Services.DoctorService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/doctor")
@AllArgsConstructor
public class DoctorController {
    private final DoctorService doctorService;

    @GetMapping("/getAll")
    public ResponseEntity getAll(){
        return ResponseEntity.status(200).body(doctorService.doctors());
    }

    @PostMapping("/add")
    public ResponseEntity add(@Valid @RequestBody DoctorDto doctor, Errors errors){
        doctorService.add(doctor, errors);
        return ResponseEntity.status(200).body("Success");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable int id, @Valid @RequestBody DoctorDto doctor, Errors errors){
        doctorService.update(id, doctor, errors);
        return ResponseEntity.status(200).body("Success");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable int id){
        doctorService.delete(id);
        return ResponseEntity.status(200).body("Success");
    }

    @GetMapping("/getAll/{doctorName}/{status}")
    public ResponseEntity getAppointments(@PathVariable String doctorName, @PathVariable String status){
        List<Appointment> appointment = doctorService.getAppointments(doctorName, status);
        return ResponseEntity.status(200).body(appointment);
    }

    @PutMapping("/newStatus/{doctorName}/{patientName}/{status}")
    public ResponseEntity newStatus(@PathVariable String doctorName, @PathVariable String patientName, @PathVariable String status){
        doctorService.newStatus(doctorName, patientName, status);
        return ResponseEntity.status(200).body("Success");
    }

}
