package com.hms.appointment.entity;

import java.time.LocalDate;

import com.hms.appointment.dto.PrescriptionDTO;
import com.hms.appointment.dto.PrescriptionDetailsDTO;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long patientId;
    private Long doctorId;
    @OneToOne
    @JoinColumn(name="appointment_id")
    private Appointment appointment;
    private LocalDate prescriptionDate;
    private String notes;
   public Prescription(Long id){
    this.id = id;
   }
public PrescriptionDTO toDTO() {
    return new PrescriptionDTO(
        id,
        patientId,
        doctorId,
        appointment.getId(),
        prescriptionDate,
        notes,
        null // Medicines can be set separately
    );
}


public PrescriptionDetailsDTO toDetailsDTO() {
    return new PrescriptionDetailsDTO(
        id,
        patientId,
        doctorId,
        null, // Doctor name can be set separately
        appointment.getId(),
        prescriptionDate,
        notes,
        null // Medicines can be set separately
    );

}


}
