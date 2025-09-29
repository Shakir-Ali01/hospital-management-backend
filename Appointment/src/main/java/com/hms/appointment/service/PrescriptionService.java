package com.hms.appointment.service;

import java.util.List;

import com.hms.appointment.dto.PrescriptionDTO;
import com.hms.appointment.dto.PrescriptionDetailsDTO;
import com.hms.appointment.exception.HmsException;

public interface PrescriptionService {
public Long savePrescription(PrescriptionDTO request) throws HmsException;
public PrescriptionDTO getPrescriptionByAppointmentId(Long appointmentId) throws HmsException;
public PrescriptionDTO getPrescriptionById(Long prescriptionId) throws HmsException;
 public List<PrescriptionDetailsDTO> getPrescriptionsByPatientId(Long patientId) throws HmsException;
} 
