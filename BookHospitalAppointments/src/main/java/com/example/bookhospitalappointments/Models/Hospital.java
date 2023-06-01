package com.example.bookhospitalappointments.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Hospital {
    @Id
    @GeneratedValue(generator = "id_hospital_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "id", sequenceName = "id_hospital_seq", initialValue = 1, allocationSize = 1)
    private Integer id;

    @NotEmpty(message = "name must be not empty")
    @Column(columnDefinition = "varchar(100)")
    private String name;

    @NotEmpty(message = "city must be not empty")
    @Column(columnDefinition = "varchar(50)")
    private String city;
    @NotEmpty(message = "location must be not empty")
    @Column(columnDefinition = "varchar(300)")
    private String location;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "hospital")
    private Set<Doctor> doctorSet;

}
