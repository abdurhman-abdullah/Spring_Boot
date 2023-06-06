package com.example.project28.Controllers;

import com.example.project28.Models.Customer;
import com.example.project28.Services.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/getAll")
    public ResponseEntity getAll(@AuthenticationPrincipal Customer customerId){
        return ResponseEntity.status(200).body(this.customerService.getAll(customerId.getId()));
    }

    @PostMapping("/register")
    public ResponseEntity add(@Valid @RequestBody Customer customer){
        this.customerService.add(customer);
        return ResponseEntity.status(200).body("customer added");
    }

    @PutMapping("/update")
    public ResponseEntity update(@AuthenticationPrincipal Customer customerId, @Valid @RequestBody Customer customer){
        this.customerService.update(customerId.getId(), customer);
        return ResponseEntity.status(200).body("customer updated");
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@AuthenticationPrincipal Customer customerId){
        this.customerService.delete(customerId.getId());
        return ResponseEntity.status(200).body("customer deleted");
    }


}
