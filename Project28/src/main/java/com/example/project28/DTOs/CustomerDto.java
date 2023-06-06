package com.example.project28.DTOs;

import com.example.project28.Models.Order;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@AllArgsConstructor
@Data
public class CustomerDto {
    private String username;
    private String role;
    private Set<Order> orders;
}
