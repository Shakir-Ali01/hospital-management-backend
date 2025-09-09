package com.hms.appointment.service;

import java.util.List;

import com.hms.appointment.dto.AppointmentDTO;
import com.hms.appointment.dto.AppointmentDetailsDTO;
import com.hms.appointment.entity.Appointment;
import com.hms.appointment.exception.HmsException;

public interface AppointmentService {

    

    //define THE METHOD FOR THE APPOINTMENT SERVICE
    Long scheduleAppointment(AppointmentDTO appointmentDTO) throws HmsException;
    void cancelAppointment(Long appointmentId) throws HmsException;
    void completeAppointment(Long appointmentId);
    void rescheduleAppointment(Long appointmentId, String newDateTime);
    String getAppointmentStatus(Long appointmentId);
    AppointmentDTO getAppointmentDetails(Long appointmentId) throws HmsException;
    AppointmentDetailsDTO getAppointmentDetailsWithName(Long appointmentId) throws HmsException;
    List<AppointmentDetailsDTO> getAllAppointmentByPatient(Long patientId) throws HmsException;
    List<AppointmentDetailsDTO> getAllAppointmentByDoctor(Long doctorId) throws HmsException;
    
}
