package com.hms.appointment.entity;

import com.hms.appointment.dto.MedicineDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Medicine {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long medicineId;
    private String name;
    private String dosage;
    private String frequency;
    private Integer duration;
    private String instructions;
    private String type; // e.g., Tablet, Syrup, Injection
    private String route; // e.g., Oral, Intravenous
    private String sideEffects;
    @ManyToOne(fetch = jakarta.persistence.FetchType.LAZY)
    @JoinColumn(name = "prescription_id")
    private Prescription prescription;

    public MedicineDTO toDTO() {
        return new MedicineDTO(
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
            prescription != null ? prescription.getId() : null
         );
    }
    
}
