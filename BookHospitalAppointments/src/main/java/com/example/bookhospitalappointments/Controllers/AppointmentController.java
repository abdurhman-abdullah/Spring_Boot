package com.example.bookhospitalappointments.Controllers;

import com.example.bookhospitalappointments.DTOs.AppointmentDto;
import com.example.bookhospitalappointments.Models.Appointment;
import com.example.bookhospitalappointments.Services.AppointmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/appointment")
public class AppointmentController {
    private final AppointmentService appointmentService;

    @GetMapping("/getAll")
    public ResponseEntity getAllAppointment(){
        List<Appointment> allAppointments=appointmentService.getAllAppointments();
        return ResponseEntity.status(200).body(allAppointments);
    }

    @PostMapping("/add")
    public ResponseEntity addAppointment(@Valid @RequestBody AppointmentDto dto, Errors errors){
        appointmentService.addAppointment(dto,errors);
        return ResponseEntity.status(200).body("Appointment added");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateAppointment(@Valid @RequestBody AppointmentDto dto, Errors errors, @PathVariable Integer id){
        appointmentService.updateAppointment(dto, errors, id);
        return ResponseEntity.status(200).body("Appointment updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteAppointment(@PathVariable @Valid Integer id){
        appointmentService.deleteAppointment(id);
        return ResponseEntity.status(200).body("Appointment deleted");
    }

    @GetMapping("/get-patient/{id}")
    public ResponseEntity getAppointmentPatient(@PathVariable @Valid Integer id) {
        String patientName = appointmentService.getAppointmentPatient(id);
        return ResponseEntity.status(200).body(patientName);
    }

    @GetMapping("/get-doctor/{id}")
    public ResponseEntity getAppointmentDoctor(@PathVariable @Valid Integer id) {
        String doctorName = appointmentService.getAppointmentDoctor(id);
        return ResponseEntity.status(200).body(doctorName);
    }
}
