package com.example.springbootproject.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@Data
public class Product {

    @NotEmpty(message = "id must not be empty")
    @Length(min = 3, message = "id have to be 3 character long")
    private String id;

    @NotEmpty(message = "name must not be empty")
    @Length(min = 3, message = "name have to be 3 length long")
    private String name;

    @NotEmpty(message = "price must not be empty")
    @Positive(message = "price must be positive number")
    private String price;

    @NotEmpty(message = "categoryID must not be empty")
    @Length(min = 3, message = "categoryID have to be 3 character long")
    private String categoryID;
}
