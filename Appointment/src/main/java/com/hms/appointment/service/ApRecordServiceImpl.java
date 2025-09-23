package com.hms.appointment.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hms.appointment.dto.ApRecordDTO;
import com.hms.appointment.entity.ApRecord;
import com.hms.appointment.exception.HmsException;
import com.hms.appointment.repository.ApRecordRepository;
import com.hms.appointment.utility.StringUtilityConverter;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
@Transactional
public class ApRecordServiceImpl implements ApRecordService {
 private final ApRecordRepository apRecordRepository;
 private final PrescriptionService prescriptionService;
    @Override
    public Long createApRecord(ApRecordDTO request) throws HmsException {
        // TODO Auto-generated method stub
        Optional<ApRecord> existingRecord=apRecordRepository.findByAppointment_Id(request.getAppointmentId());
        if(existingRecord.isPresent()){
            throw new HmsException("APPOINTMENT_RECORD_ALREADY_EXISTS");
        }
        
        Long id= apRecordRepository.save(request.toEntity()).getId();
        if(request.getPrescription()!=null){
            request.getPrescription().setAppointmentId(request.getAppointmentId());
            prescriptionService.savePrescription(request.getPrescription());
        }
        return id;
    }
    @Override
    public void updateApRecord(ApRecordDTO request) throws HmsException {
        // TODO Auto-generated method stub
        ApRecord existing=apRecordRepository.findByAppointment_Id(request.getId()).orElseThrow(()-> new HmsException("APPOINTMENT_RECORD_NOT_FOUND"));
        existing.setNotes(request.getNotes());
        existing.setDiagnosis(request.getDiagnosis());
        existing.setFollowUpDate(request.getFollowUpDate());
        existing.setSymptoms(StringUtilityConverter.convertListToString(request.getSymptoms()));
        existing.setTests(StringUtilityConverter.convertListToString(request.getTests()));
        existing.setReferral(request.getReferral());
        apRecordRepository.save(existing);
    }
   
    @Override
    public ApRecordDTO getApRecordById(Long recordId) throws HmsException {
        // TODO Auto-generated method stub
        
         return apRecordRepository.findByAppointment_Id(recordId).orElseThrow(()-> new HmsException("APPOINTMENT_NOT_FOUND")).toDTO();
    }
    @Override
    public ApRecordDTO getApRecordByAppointmentId(Long appintmentId) throws HmsException {
        // TODO Auto-generated method stub
         return apRecordRepository.findByAppointment_Id(appintmentId).orElseThrow(()-> new HmsException("APPOINTMENT_NOT_FOUND")).toDTO();
        
    }
    @Override
    public ApRecordDTO getApRecordDetailsByAppointmentId(Long appointmentId) throws HmsException {
        // TODO Auto-generated method stub
        //apRecordRepository.findByAppointment_Id(appointmentId).orElseThrow(()-> new HmsException("APPOINTMENT_NOT_FOUND"));
        ApRecordDTO apRecordDTO=apRecordRepository.findByAppointment_Id(appointmentId).orElseThrow(()-> new HmsException("APPOINTMENT_NOT_FOUND")).toDTO();
        apRecordDTO.setPrescription(prescriptionService.getPrescriptionByAppointmentId(appointmentId)); 
        return apRecordDTO;
    }
    
}
