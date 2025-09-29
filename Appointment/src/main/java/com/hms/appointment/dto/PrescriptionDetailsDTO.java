package com.hms.appointment.dto;

import java.time.LocalDate;
import java.util.List;

import com.hms.appointment.entity.Appointment;
import com.hms.appointment.entity.Prescription;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrescriptionDetailsDTO {
    private Long id;
    private Long PatientId;
    private Long doctorId;
    private String doctorName;
    private Long appointmentId;
    private LocalDate prescriptionDate;
    private String notes;
    private List<MedicineDTO> medicines;
      
}
