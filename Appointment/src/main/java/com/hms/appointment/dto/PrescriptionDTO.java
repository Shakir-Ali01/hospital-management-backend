package com.hms.appointment.dto;

import java.time.LocalDate;
import java.util.List;

import com.hms.appointment.entity.Appointment;
import com.hms.appointment.entity.Prescription;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrescriptionDTO {
    private Long id;
    private Long PatientId;
    private Long doctorId;
    private Long appointmentId;
    private LocalDate prescriptionDate;
    private String notes;
    private List<MedicineDTO> medicines;
    public Prescription ToEntity(){
        return new Prescription(
            id,
            PatientId,
            doctorId,
            new Appointment(appointmentId),
            prescriptionDate,
            notes
        );
    }   
}
