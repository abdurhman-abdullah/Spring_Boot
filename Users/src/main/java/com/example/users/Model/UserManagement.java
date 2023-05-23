package com.example.users.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserManagement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "name cannot be null")
    @Length(min = 5, message = "name length more than 4")
    @Column(columnDefinition = "varchar(20) not null")
    private String NAME;

    @NotEmpty(message = "username cannot be null")
    @Length(min = 5, message = "username length more than 4")
    @Column(columnDefinition = "varchar(20) not null unique")
    private String USERNAME;

    @NotEmpty(message = "password cannot be null")
    @Length(min = 5, message = "password length more than 4")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,}$", message = "password must have characters and digits ")
    @Column(columnDefinition = "varchar(20) not null")
    private String PASSWORD;

    @NotEmpty(message = "email cannot be null")
    @Email(message = "email must be valid")
    @Column(columnDefinition = "varchar(20) not null unique")
    private String EMAIL;

    @NotEmpty(message = "role cannot be null")
    @Column(columnDefinition = "varchar(20) not null check(role = 'user' or role = 'admin')")
    private String ROLE;

    @NotNull(message = "age cannot be null")
    @Column(columnDefinition = "int not null")
    private Integer AGE;

}
