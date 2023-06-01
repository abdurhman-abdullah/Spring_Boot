package com.example.bookhospitalappointments.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@Entity
@NoArgsConstructor
@Setter
@Getter
public class Patient {

    @Id
    @GeneratedValue(generator = "id_patient_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "id", sequenceName = "id_patient_seq", initialValue = 1, allocationSize = 1)
    private Integer id;

    @NotEmpty(message = "Patient name must be not empty")
    @Column(columnDefinition = "varchar(100) not null")
    private String name;

    @NotEmpty(message = "Patient phone must be not empty")
    @Column(columnDefinition = "varchar(20) not null")
    private String phone;

    @NotNull(message ="Patient age must be not empty" )
    @Positive(message = "Enter positive number")
    @Column(columnDefinition = "int")
    private Integer age;

    @NotEmpty(message = "Email must be not empty")
    @Email(message = "Enter valid email")
    @Column(columnDefinition = "varchar(100) unique")
    private String email;

    @OneToMany(mappedBy = "patient")
    private Set<Appointment> appointmentSet;
}
