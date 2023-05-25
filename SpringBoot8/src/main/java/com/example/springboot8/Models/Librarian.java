package com.example.springboot8.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
public class Librarian {
    @Id
    @GeneratedValue(generator = "id_lib_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "id", sequenceName = "id_lib_seq", initialValue = 1, allocationSize = 1)
    private Integer id;

    @NotEmpty(message = "name not empty")
    @Column(columnDefinition = "varchar(50) not null")
    private String name;

    @NotEmpty(message = "name not empty")
    @Column(columnDefinition = "varchar(50) unique")
    private String username;

    @NotEmpty(message = "password not empty")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,}$", message = "Password must be 6 characters long and combination of uppercase letters, lowercase letters, numbers")
    @Column(columnDefinition = "varchar(50) unique")
    private String password;

    @NotEmpty(message = "email not empty")
    @Email(message = "email not valid")
    @Column(columnDefinition = "varchar(50) unique")
    private String email;
}
