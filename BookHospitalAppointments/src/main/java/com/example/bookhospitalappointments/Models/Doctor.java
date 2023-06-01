package com.example.bookhospitalappointments.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.Set;

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
    @Column(columnDefinition = "varchar(300) unique")
    private String name;

    @NotEmpty(message = "clinic must be not empty")
    @Column(columnDefinition = "varchar(200)")
    private String clinic;

    @NotEmpty(message = "rank must be not empty")
    @Column(columnDefinition = "varchar(20)")
    private String rank;

    @ManyToOne
    @JoinColumn(name = "hospitals_id",referencedColumnName = "id")
    @JsonIgnore
    private Hospital hospital;


    @OneToMany(cascade = CascadeType.ALL,mappedBy = "doctor")
    private Set<Appointment> appointment;

    public Doctor(String name,String clinic,String rank,Hospital hospital){
        this.name=name;
        this.clinic=clinic;
        this.rank=rank;
        this.hospital = hospital;
    }
}
