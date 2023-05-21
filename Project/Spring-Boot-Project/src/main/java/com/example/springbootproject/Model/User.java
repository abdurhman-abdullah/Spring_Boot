package com.example.springbootproject.Model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Value;

@AllArgsConstructor
@Data
public class User {

    @NotEmpty(message = "id must not be empty")
    @Length(min = 3, message = "id have to be 3 character long")
    private String id;

    @NotEmpty(message = "userName must not be empty")
    @Length(min = 5,message = "userName have to be 5 length long")
    private String userName;

    @NotEmpty(message = "password must not be empty")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,}$", message = "Password must be 6 characters long and combination of uppercase letters, lowercase letters, numbers.")
    private String password;

    @NotEmpty(message = "email must not be empty")
    @Email(message = "email must be valid email")
    private String email;

    //Test2
    @NotEmpty(message = "role must not be empty")
    @Value("Admin")
    @Valid
    private String role;

    @NotEmpty(message = "balance must not be empty")
    @Positive(message = "balance must be positive number")
    private String balance;
}
