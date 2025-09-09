package com.hms.ProfileMs.service;

import java.util.List;

import com.hms.ProfileMs.dto.DoctorDTO;
import com.hms.ProfileMs.dto.DoctorDropdown;
import com.hms.ProfileMs.dto.PatientDTO;
import com.hms.ProfileMs.exceptions.HmsExceptions;

public interface DoctorService {
    public Long addDoctor(DoctorDTO doctorDTO) throws HmsExceptions;
    public DoctorDTO getDoctorById(Long id) throws HmsExceptions;  
    public List<DoctorDTO> getAllDoctors() throws HmsExceptions;
    public DoctorDTO updateDoctor(DoctorDTO doctorDTO) throws HmsExceptions;
    public Boolean doctorExists(Long id) throws HmsExceptions;
    public List<DoctorDropdown> getDoctorDropdown() throws HmsExceptions;
}
