package com.hms.ProfileMs.service;

import java.util.List;

import com.hms.ProfileMs.dto.PatientDTO;
import com.hms.ProfileMs.exceptions.HmsExceptions;

public interface PatientService {
    public Long addPatient(PatientDTO patientDTO) throws HmsExceptions;
    public PatientDTO getPatientById(Long id) throws HmsExceptions;
    public List<PatientDTO> getAllPatients() throws HmsExceptions;
    public PatientDTO updatePatient(PatientDTO patientDTO) throws HmsExceptions;  
     public Boolean patientExists(Long id) throws HmsExceptions;
}
