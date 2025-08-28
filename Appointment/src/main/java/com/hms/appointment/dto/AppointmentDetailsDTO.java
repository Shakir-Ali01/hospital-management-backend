package com.hms.appointment.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDetailsDTO {
    private Long id;
    private Long patientId;
    private String PatientName;
    private String patientEmail;
    private String patientPhone;
    private Long doctorId;  
     private String doctorName;
    private LocalDateTime appointmentDateTime;
    private Status status;
    private String reason;
    private String notes;

}
