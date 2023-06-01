package com.example.bookhospitalappointments.Controllers;

import com.example.bookhospitalappointments.Models.Hospital;
import com.example.bookhospitalappointments.Services.HospitalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/hospital")
public class HospitalController {

    private final HospitalService hospitalService;

    @GetMapping("/getAll")
    public ResponseEntity getAll(){
        List<Hospital> hospitalsList = hospitalService.getAll();
        return ResponseEntity.status(200).body(hospitalsList);
    }

    @PostMapping("/add")
    public ResponseEntity add(@Valid @RequestBody Hospital hospital, Errors errors){
        hospitalService.add(hospital, errors);
        return ResponseEntity.status(200).body("success");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable Integer id, @Valid @RequestBody Hospital hospital, Errors errors){
        hospitalService.update(id, hospital, errors);
        return ResponseEntity.status(200).body("success");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Integer id){
        hospitalService.delete(id);
        return ResponseEntity.status(200).body("success");
    }
}
