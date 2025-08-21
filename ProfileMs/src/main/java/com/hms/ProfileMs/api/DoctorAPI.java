package com.hms.ProfileMs.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hms.ProfileMs.dto.DoctorDTO;
import com.hms.ProfileMs.dto.PatientDTO;
import com.hms.ProfileMs.exceptions.HmsExceptions;
import com.hms.ProfileMs.service.DoctorService;

import lombok.RequiredArgsConstructor;
  
@RestController
@RequestMapping("/profile/doctors")
@RequiredArgsConstructor
public class DoctorAPI {
    private final DoctorService doctorService;
    @PostMapping("/create")
    public ResponseEntity<Long> createDoctor(@RequestBody DoctorDTO doctorDTO) throws HmsExceptions {
           // Call the service to add the patient
           return new ResponseEntity<>(doctorService.addDoctor(doctorDTO), HttpStatus.CREATED);
}
    @GetMapping("/get/{id}")
    public ResponseEntity<DoctorDTO> getDoctorById(@PathVariable Long id) throws HmsExceptions {
        // Call the service to get the patient by ID
        return new ResponseEntity<>(doctorService.getDoctorById(id), HttpStatus.OK);
    }
}
