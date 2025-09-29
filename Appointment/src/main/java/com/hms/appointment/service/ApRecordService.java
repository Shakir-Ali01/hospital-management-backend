package com.hms.appointment.service;

import java.util.List;

import com.hms.appointment.dto.ApRecordDTO;
import com.hms.appointment.dto.RecordDetailsDTO;
import com.hms.appointment.exception.HmsException;

public interface ApRecordService {
    public Long createApRecord(ApRecordDTO request) throws HmsException;
    public void updateApRecord(ApRecordDTO request) throws HmsException;
    public ApRecordDTO getApRecordByAppointmentId(Long appintmentId) throws HmsException;
    public ApRecordDTO getApRecordById(Long Id) throws HmsException;
    public ApRecordDTO getApRecordDetailsByAppointmentId(Long appointmentId) throws HmsException;
    public  List<RecordDetailsDTO> getApRecordsByPatientId(Long patientId) throws HmsException;
    public Boolean isAppointmentRecordExists(Long appointmentId) throws HmsException;
}
