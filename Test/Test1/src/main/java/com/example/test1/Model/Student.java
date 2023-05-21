package com.example.test1.Model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Student {

    @NotEmpty(message = "id is empty")
    private String id;

    @NotEmpty(message = "name is empty")
    private String name;

    @NotEmpty(message = "age is empty")
    @Min(value = 5, message = "age must greater than 4")
    @Max(value = 18, message = "Age must be less than 18")
    private String age;

    @NotEmpty(message = "major is empty")
    private String major;
}
