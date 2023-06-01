package com.example.bookhospitalappointments.Repository;

import com.example.bookhospitalappointments.Models.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Integer> {
}
