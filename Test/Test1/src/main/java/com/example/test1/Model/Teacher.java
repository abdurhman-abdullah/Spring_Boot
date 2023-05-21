package com.example.test1.Model;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Teacher {

    @NotEmpty(message = "id is empty")
    private String id;

    @NotEmpty(message = "name is empty")
    private String name;

    @NotEmpty(message = "salary is empty")
    private String salary;

}
