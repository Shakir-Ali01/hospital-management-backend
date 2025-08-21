package com.hms.ProfileMs.repository;

import java.util.Optional;

import javax.print.Doc;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hms.ProfileMs.entity.Doctor;
import java.util.List;


public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    // Additional query methods can be defined here if needed
   Optional<Doctor> findByEmail(String email);
    Optional<Doctor>  findByLicenceNumber(String licenceNumber);
}
