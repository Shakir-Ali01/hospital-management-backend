package com.hms.appointment.service;

import org.springframework.stereotype.Service;

import com.hms.appointment.dto.PrescriptionDTO;
import com.hms.appointment.exception.HmsException;
import com.hms.appointment.repository.PrescriptionRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class PrescriptionServiceImp  implements PrescriptionService {
 private final PrescriptionRepository prescriptionRepository;
private final MedicineService medicineService;
 @Override
 public Long savePrescription(PrescriptionDTO request) throws HmsException {
    // TODO Auto-generated method stub
    Long prescriptionId= prescriptionRepository.save(request.ToEntity()).getId();
    request.getMedicines().forEach(medicines-> medicines.setPrescriptionIId(prescriptionId));
    medicineService.saveAllMedicines(request.getMedicines());
    return prescriptionId;
    
 }

 @Override
 public PrescriptionDTO getPrescriptionByAppointmentId(Long appointmentId) throws HmsException {
    // TODO Auto-generated method stub
    PrescriptionDTO prescriptionDTO=prescriptionRepository.findByAppointment_Id(appointmentId).orElseThrow(()-> new HmsException("PRESCRIPTION_NOT_FOUND")).toDTO();
    prescriptionDTO.setMedicines(medicineService.getMedicinesByPrescriptionId(prescriptionDTO.getId()));
    return prescriptionDTO;
 }

 

 @Override
 public PrescriptionDTO getPrescriptionById(Long prescriptionId) throws HmsException {
   // TODO Auto-generated method stub
   PrescriptionDTO prescriptionDTO=prescriptionRepository.findById(prescriptionId).orElseThrow(()-> new HmsException("PRESCRIPTION_NOT_FOUND")).toDTO();
   prescriptionDTO.setMedicines(medicineService.getMedicinesByPrescriptionId(prescriptionDTO.getId()));
   return prescriptionDTO;
 }
 
    
}
