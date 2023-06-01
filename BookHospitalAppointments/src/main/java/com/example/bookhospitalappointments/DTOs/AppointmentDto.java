package com.example.bookhospitalappointments.DTOs;

import jakarta.persistence.Column;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
@AllArgsConstructor
@Data
public class AppointmentDto {

    @FutureOrPresent
    @Column(columnDefinition = "date not null")
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date bookingDate;

    @NotNull(message = "patient_id must be not found")
    private int patient_id;

    @NotNull(message = "doctor_id must be not found")
    private int doctor_id;

}
