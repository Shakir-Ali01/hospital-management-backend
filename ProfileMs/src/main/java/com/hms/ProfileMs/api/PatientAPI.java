package com.hms.ProfileMs.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hms.ProfileMs.dto.DoctorDTO;
import com.hms.ProfileMs.dto.PatientDTO;
import com.hms.ProfileMs.exceptions.HmsExceptions;
import com.hms.ProfileMs.service.PatientService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/profile/patient")
@Validated
@RequiredArgsConstructor
public class PatientAPI {
    private final PatientService patientService;
    
    @PostMapping("/create")
    public ResponseEntity<Long> createPatient(@RequestBody PatientDTO patientDto) throws HmsExceptions {
           // Call the service to add the patient
           return new ResponseEntity<>(patientService.addPatient(patientDto), HttpStatus.CREATED);
}
    @GetMapping("/get/{id}")
    public ResponseEntity<PatientDTO> getPatientById(@PathVariable Long id) throws HmsExceptions {
        // Call the service to get the patient by ID
        return new ResponseEntity<>(patientService.getPatientById(id), HttpStatus.OK);
    }
     @PutMapping("/update")
    public ResponseEntity<PatientDTO> updateDoctor(@RequestBody PatientDTO patientDTO) throws HmsExceptions {
        // Call the service to update the patient
        return new ResponseEntity<>(patientService.updatePatient(patientDTO), HttpStatus.OK);
    }
    @GetMapping("/exists/{id}")
    public Boolean isPatientExists(@PathVariable Long id ) throws HmsExceptions {
        return patientService.patientExists(id);
    }
}
