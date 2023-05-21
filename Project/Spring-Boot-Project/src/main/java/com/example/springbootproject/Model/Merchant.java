package com.example.springbootproject.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
@AllArgsConstructor
@Data
public class Merchant{
    @NotEmpty(message = "id must not be empty")
    @Length(min = 3, message = "id have to be 3 character long")
    private String id;

    @NotEmpty(message = "name must not be empty")
    @Size(min = 3, message = "name have to be 3 character long")
    private String name;
}
