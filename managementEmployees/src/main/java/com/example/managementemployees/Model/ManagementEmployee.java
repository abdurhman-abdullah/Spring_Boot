package com.example.managementemployees.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.validation.annotation.Validated;

import java.util.Date;

@AllArgsConstructor
@Data
@Validated
public class ManagementEmployee {

    @NotNull(message = "Cannot be null")
    private int id;

    @NotEmpty(message = "Cannot be null")
    @Length(min = 4)
    private String name;

    @NotNull(message = "Cannot be null")
    @NumberFormat
    @Min(value = 26)
    private int age;

    @NotEmpty(message = "Cannot be null")
    private String position;

    @AssertFalse
    private boolean onLeave;

    @FutureOrPresent(message = "Cannot be null")
    private Date employmentYear;

    @NotNull(message = "Cannot be null")
    @NumberFormat
    private int annualLeave;
}
