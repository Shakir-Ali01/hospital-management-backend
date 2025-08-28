package com.hms.ProfileMs.entity;

import java.time.LocalDate;

import com.hms.ProfileMs.dto.BloodGroup;
import com.hms.ProfileMs.dto.PatientDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true)
    private String email;
    private LocalDate dob;
    private String phone;
    private String address; 
     @Column(unique = true)
    private String aadhaarNumber;
    private BloodGroup bloodGroup;
    private String allergies;
    private String chronicDiseases;
    public PatientDTO toDto() {
        return new PatientDTO(
                this.id,
                this.name,
                this.email,
                this.dob,
                this.phone,
                this.address,
                this.aadhaarNumber,
                this.bloodGroup,
                this.allergies,
                this.chronicDiseases
        );
    }
}
