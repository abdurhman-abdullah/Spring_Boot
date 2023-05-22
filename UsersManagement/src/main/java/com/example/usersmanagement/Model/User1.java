package com.example.usersmanagement.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User1 {
    @Id
    @NotEmpty(message = "id cannot be null")
    private Integer id;

    @NotEmpty(message = "name cannot be null")
    @Length(min = 5, message = "name length more than 4")
    @Column(columnDefinition = "varchar(20) not null")
    private String name;

    @NotEmpty(message = "username cannot be null")
    @Length(min = 5, message = "username length more than 4")
    @Column(columnDefinition = "varchar(20) not null unique")
    private String username;

    @NotEmpty(message = "password cannot be null")
    @Length(min = 5, message = "password length more than 4")
    @Column(columnDefinition = "varchar(20) not null")
    private String password;

    @NotEmpty(message = "email cannot be null")
    @Email(message = "email must be valid")
    @Column(columnDefinition = "varchar(20) not null unique")
    private String email;

    @NotEmpty(message = "role cannot be null")
    @Pattern(regexp = "")
    @Column(columnDefinition = "varchar(20) not null check(role = 'user' or role = 'admin')")
    private String role;

    @NotEmpty(message = "age cannot be null")
    @Column(columnDefinition = "int not null")
    private Integer age;

}
