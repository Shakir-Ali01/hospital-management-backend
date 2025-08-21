package com.hms.ProfileMs.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hms.ProfileMs.entity.Patient;
import java.util.List;


public interface PatientRepository extends JpaRepository<Patient, Long> {

    // Additional query methods can be defined here if needed
    Optional<Patient> findByEmail(String email);
    Optional<Patient> findByAadhaarNumber(String aadhaarNumber);
}
