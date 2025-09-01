package com.hms.appointment.clients;


import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.hms.appointment.dto.DoctorDTO;
import com.hms.appointment.dto.PatientDTO;

// @FeignCilient(name="ProfileMS")

@FeignClient(name="PROFILEMS")
public interface ProfileClient {
    @GetMapping("/profile/doctors/exists/{id}")
    Boolean  isDoctorExists(@PathVariable("id") Long id);
    @GetMapping("/profile/patient/exists/{id}")
    Boolean isPatientExists(@PathVariable("id") Long id);
    @GetMapping("/profile/patient/get/{id}")
    PatientDTO getPatientById(@PathVariable("id") Long id);
    @GetMapping("/profile/doctors/get/{id}")
    DoctorDTO getDoctorById(@PathVariable("id") Long id);
}
