package com.example.bookhospitalappointments.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Doctor {
    @Id
    @GeneratedValue(generator = "doctor_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "id", sequenceName = "doctor_seq", initialValue = 1, allocationSize = 1)
    private Integer id;

    @NotEmpty(message = "name must be not empty")
    @Column(columnDefinition = "varchar(100) unique")
    private String name;

    @NotEmpty(message = "clinic must be not empty")
    @Column(columnDefinition = "varchar(50)")
    private String clinic;

    @NotEmpty(message = "rank must be not empty")
    @Column(columnDefinition = "varchar(20)")
    private String rank;
}
