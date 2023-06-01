package com.example.bookhospitalappointments.DTOs;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class DoctorDto {
    @NotEmpty(message = "name must be not empty")
    private String name;

    @NotEmpty(message = "clinic must be not empty")
    private String clinic;

    @NotEmpty(message = "rank must be not empty")
    private String rank;

    @NotNull(message = "hospital_id must be not found")
    private Integer hospital_id;
}
