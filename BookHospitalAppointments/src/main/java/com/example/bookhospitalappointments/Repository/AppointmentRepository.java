package com.example.bookhospitalappointments.Repository;

import com.example.bookhospitalappointments.Models.Appointment;
import com.example.bookhospitalappointments.Models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    Appointment findAppointmentById(Integer id);
    List<Appointment> findByStatus(String doctor);
    @Query("select a from Appointment a where a.doctor.name = ?1 and a.patient.name = ?2")
    Appointment findByDoctorAndPatient(String doctor_name, String patient_name);
}
