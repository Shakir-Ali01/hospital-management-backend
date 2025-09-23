package com.hms.appointment.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hms.appointment.entity.Prescription;

public interface PrescriptionRepository  extends JpaRepository<Prescription, Long>{
    //Method to find prescription by appointment ID
    Optional<Prescription> findByAppointment_Id(Long appointmentId);
    //Method to find all prescriptions by patient ID
    List<Prescription> findAllByPatientId(Long pateinetId);
}
