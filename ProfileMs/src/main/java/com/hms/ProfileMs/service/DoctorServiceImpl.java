package com.hms.ProfileMs.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hms.ProfileMs.dto.DoctorDTO;
import com.hms.ProfileMs.exceptions.HmsExceptions;
import com.hms.ProfileMs.repository.DoctorRepository;
import com.hms.ProfileMs.repository.PatientRepository;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

     private final DoctorRepository doctorRepository;
    // Implement the methods defined in the DoctorService interface
    @Override
    public Long addDoctor(DoctorDTO doctorDTO) throws HmsExceptions {
        // Implementation logic for adding a doctor
        if (doctorDTO.getEmail() != null && doctorRepository.findByEmail(doctorDTO.getEmail()).isPresent()) {
            throw new HmsExceptions("DOCTOR_ALREADY_EXISTS");
        }
        if(doctorDTO.getLicenceNumber() != null && doctorRepository.findByLicenceNumber(doctorDTO.getLicenceNumber()).isPresent()) {
            throw new HmsExceptions("LICENCE_NUMBER_ALREADY_EXISTS");
        }
        return doctorRepository.save(doctorDTO.toEntity()).getId() ;
       }

    @Override
    public DoctorDTO getDoctorById(Long id) throws HmsExceptions {
        // Implementation logic for retrieving a doctor by ID
        return doctorRepository.findById(id).orElseThrow(()->new HmsExceptions("DOCTOR_NOT_FOUND")).toDto(); // Placeholder return statement
    }

    @Override
    public List<DoctorDTO> getAllDoctors() throws HmsExceptions {
        // Implementation logic for retrieving all doctors
        return null; // Placeholder return statement
    }
    
}
