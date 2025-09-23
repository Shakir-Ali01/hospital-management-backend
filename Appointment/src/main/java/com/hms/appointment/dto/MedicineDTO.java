package com.hms.appointment.dto;

import com.hms.appointment.entity.Medicine;
import com.hms.appointment.entity.Prescription;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class MedicineDTO {
    private Long id;
    private Long medicineId;
    private String name;
    private String dosage;
    private String frequency;
    private Integer duration;
    private String instructions;
    private String type; // eg, Tablet, Syrup, Injection
    private String route; // eg, Oral, Intravenous
    private String sideEffects;
    private Long prescriptionIId;
    public Medicine toEntity(){
        return new Medicine(
            id,
            medicineId,
            name,
            dosage,
            frequency,
            duration,
            instructions,
            type,
            route,
            sideEffects,
            new Prescription(prescriptionIId)
        );
    }
    }

