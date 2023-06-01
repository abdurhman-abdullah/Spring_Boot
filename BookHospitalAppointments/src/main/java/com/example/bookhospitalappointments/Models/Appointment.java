package com.example.bookhospitalappointments.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

@Entity
@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(generator = "id_appointment_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "id", sequenceName = "id_appointment_seq", initialValue = 1, allocationSize = 1)
    private Integer id;

    @FutureOrPresent
    @Column(columnDefinition = "date not null")
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date bookingDate;

    private String status;

    @ManyToOne
    @JoinColumn(name = "patient_id",referencedColumnName = "id")
    @JsonIgnore
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id",referencedColumnName = "id")
    @JsonIgnore
    private Doctor doctor;

    public Appointment(Date bookingDate, String status, Patient patient, Doctor doctor) {
        this.bookingDate = bookingDate;
        this.status = status;
        this.patient = patient;
        this.doctor = doctor;
    }
}
