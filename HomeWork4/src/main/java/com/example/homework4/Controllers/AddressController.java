package com.example.homework4.Controllers;

import com.example.homework4.DTO.AddressInsertDto;
import com.example.homework4.DTO.AddressUpdateDto;
import com.example.homework4.Services.AddressService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/address")
@AllArgsConstructor
public class AddressController {
    private final AddressService addressService;

    @PostMapping("/add")
    public ResponseEntity add(@Valid @RequestBody AddressInsertDto dto, Errors errors){
        addressService.add(dto, errors);
        return ResponseEntity.status(200).body("success");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable int id, @Valid @RequestBody AddressUpdateDto dto, Errors errors){
        addressService.update(id, dto, errors);
        return ResponseEntity.status(200).body("success");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable int id){
        addressService.delete(id);
        return ResponseEntity.status(200).body("success");
    }

}
