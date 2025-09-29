package com.hms.appointment.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hms.appointment.clients.ProfileClient;
import com.hms.appointment.dto.PrescriptionDTO;
import com.hms.appointment.dto.PrescriptionDetailsDTO;
import com.hms.appointment.entity.Prescription;
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
private final ProfileClient profileClient;
 @Override
 public Long savePrescription(PrescriptionDTO request) throws HmsException {
    // TODO Auto-generated method stub
    request.setPrescriptionDate(java.time.LocalDate.now());
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

 @Override
 public List<PrescriptionDetailsDTO> getPrescriptionsByPatientId(Long patientId) throws HmsException {
   // TODO Auto-generated method stub
   List<Prescription> prescriptions=prescriptionRepository.findAllByPatientId(patientId);
   List<PrescriptionDetailsDTO> prescriptionDetailsDTOs=prescriptions.stream().map(Prescription::toDetailsDTO).toList();
   //setiting medicines for each prescription
   prescriptionDetailsDTOs.forEach(prescription->{
    try {
      prescription.setMedicines(medicineService.getMedicinesByPrescriptionId(prescription.getId()));
    } catch (HmsException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
   });
   //setting doctor names
   List<Long> doctorIds=prescriptionDetailsDTOs.stream().map(PrescriptionDetailsDTO::getDoctorId).distinct().toList();
   List<com.hms.appointment.dto.DoctorName> doctorNames=profileClient.getDoctorsById(doctorIds);
   prescriptionDetailsDTOs.forEach(prescription->{
    doctorNames.stream().filter(doc->doc.getId().equals(prescription.getDoctorId())).findFirst()
    .ifPresent(doc->prescription.setDoctorName(doc.getName()));
   });
   return prescriptionDetailsDTOs;
 }
 
    
}
