package com.example.project28.DTOs;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class OrderUpdateDto {
    @NotNull(message = "quantity must be not null")
    @Positive
    private Integer quantity;

    @NotNull(message = "productId must be not null")
    private Integer productId;
}
