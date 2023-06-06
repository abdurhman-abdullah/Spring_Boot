package com.example.project28.Controllers;

import com.example.project28.DTOs.OrderInsertDto;
import com.example.project28.DTOs.OrderUpdateDto;
import com.example.project28.Models.Customer;
import com.example.project28.Models.Order;
import com.example.project28.Services.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/getAll")
    public ResponseEntity getAll(@AuthenticationPrincipal Customer customerId){
        return ResponseEntity.status(200).body(this.orderService.getAllOrder(customerId.getId()));
    }

    @PostMapping("/add")
    public ResponseEntity add(@AuthenticationPrincipal Customer customer , @Valid @RequestBody OrderInsertDto order){
        this.orderService.add(customer, order);
        return ResponseEntity.status(200).body("order added");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@AuthenticationPrincipal Customer customer, @PathVariable int id, @Valid @RequestBody OrderUpdateDto order){
        this.orderService.update(id, customer.getId(), order);
        return ResponseEntity.status(200).body("order updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable int id, @AuthenticationPrincipal Customer customer){
        this.orderService.delete(id, customer.getId());
        return ResponseEntity.status(200).body("order deleted");
    }

    @PutMapping("/updateStatus/{id}")
    public ResponseEntity update(@PathVariable int id, @RequestParam String status){
        this.orderService.updateStatus(id,  status);
        return ResponseEntity.status(200).body("status updated");
    }

    @GetMapping("/getOrder/{id}")
    public ResponseEntity update(@AuthenticationPrincipal Customer customerId, @PathVariable int id){
        Order order = this.orderService.getOrderById(customerId.getId(),id);
        return ResponseEntity.status(200).body(order);
    }
}
