package com.hms.ProfileMs.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hms.ProfileMs.dto.PatientDTO;
import com.hms.ProfileMs.exceptions.HmsExceptions;
import com.hms.ProfileMs.repository.PatientRepository;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

      private final PatientRepository patientRepository;
    // Implement the methods defined in the PatientService interface
    @Override
    public Long addPatient(PatientDTO patientDTO) throws HmsExceptions {
        // Implementation logic here
        if(patientDTO.getEmail() != null && patientRepository.findByEmail(patientDTO.getEmail()).isPresent()) {
            throw new HmsExceptions("PATIENT_ALREADY_EXISTS");
        }
        if(patientDTO.getAadhaarNumber() != null && patientRepository.findByAadhaarNumber(patientDTO.getAadhaarNumber()).isPresent()) {
            throw new HmsExceptions("AADHAAR_NUMBER_ALREADY_EXISTS");
        }
        return patientRepository.save(patientDTO.toEntity()).getId();
    }

    @Override
    public PatientDTO getPatientById(Long id) throws HmsExceptions {
        // Implementation logic here
        
        return patientRepository.findById(id).orElseThrow(()->new HmsExceptions("PATIENT_NOT_FOUND")).toDto(); // Replace with actual implementation
    }

    @Override
    public List<PatientDTO> getAllPatients() throws HmsExceptions {
        // Implementation logic here
        return null; // Replace with actual implementation
    }

    @Override
    public PatientDTO updatePatient(PatientDTO patientDTO) throws HmsExceptions {
        // TODO Auto-generated method stub
         patientRepository.findById(patientDTO.getId()).orElseThrow(()->new HmsExceptions("PATIENT_NOT_FOUND"));
        return patientRepository.save(patientDTO.toEntity()).toDto();
    }

    @Override
    public Boolean patientExists(Long id) throws HmsExceptions {
        // TODO Auto-generated method stub
        return patientRepository.existsById(id);
    }
    
}
