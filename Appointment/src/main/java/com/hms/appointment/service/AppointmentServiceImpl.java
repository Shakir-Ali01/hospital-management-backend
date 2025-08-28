package com.hms.appointment.service;

import org.springframework.stereotype.Service;

import com.hms.appointment.dto.AppointmentDTO;
import com.hms.appointment.dto.AppointmentDetailsDTO;
import com.hms.appointment.dto.DoctorDTO;
import com.hms.appointment.dto.PatientDTO;
import com.hms.appointment.dto.Status;
import com.hms.appointment.entity.Appointment;
import com.hms.appointment.exception.HmsException;
import com.hms.appointment.repository.AppointmentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final ApiService apiService;
    @Override
    public Long scheduleAppointment(AppointmentDTO appointmentDTO) throws HmsException {
        // TODO Auto-generated method stub
        Boolean doctorExists=apiService.doctorExists(appointmentDTO.getDoctorId()).block();
         if(doctorExists==null || !doctorExists){
            throw new HmsException("DOCTOR_NOT_FOUND");
         }
         Boolean patientExists=apiService.patientExists(appointmentDTO.getPatientId()).block();
         if(patientExists == null || !patientExists){
            throw new HmsException("PATIENT_NOT_FOUND");
         }
          Appointment appointment = appointmentDTO.toEntity();
          if (appointment.getStatus() == null) {
              appointment.setStatus(Status.SCHEDULED);
           }
        return appointmentRepository.save(appointment).getId();
    }

    @Override
    public void cancelAppointment(Long appointmentId) throws HmsException {
        // TODO Auto-generated method stub
       Appointment appointment=appointmentRepository.findById(appointmentId).orElseThrow(()->new HmsException("APPOINTMENT_NOT_FOUND"));
       if(appointment.getStatus().equals(Status.CANCELLED)){
        throw new HmsException("APPOINTMENT_NOT_FOUND");
       }
       appointment.setStatus(Status.CANCELLED);
       appointmentRepository.save(appointment);
    }

    @Override
    public void completeAppointment(Long appointmentId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'completeAppointment'");
    }

    @Override
    public void rescheduleAppointment(Long appointmentId, String newDateTime) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'rescheduleAppointment'");
    }

    @Override
    public String getAppointmentStatus(Long appointmentId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAppointmentStatus'");
    }

    @Override
    public AppointmentDTO  getAppointmentDetails(Long appointmentId) throws HmsException {
        // TODO Auto-generated method stub
        return appointmentRepository.findById(appointmentId).orElseThrow(()->new HmsException("APPOINMENT_NOT_FOUND")).toDTO();
    }

    @Override
    public AppointmentDetailsDTO getAppointmentDetailsWithName(Long appointmentId) throws HmsException {
        // TODO Auto-generated method stub
            AppointmentDTO appointmentDTO= appointmentRepository.findById(appointmentId)
           .orElseThrow(()->new HmsException("APPOINMENT_NOT_FOUND")).toDTO();

           DoctorDTO doctorDTO=apiService.getDoctorById(appointmentDTO.getDoctorId()).block();
           PatientDTO patientDTO=apiService.getPatientById(appointmentDTO.getPatientId()).block();

           return new AppointmentDetailsDTO(appointmentDTO.getId(),appointmentDTO.getPatientId(),
           patientDTO.getName(),patientDTO.getEmail(),patientDTO.getPhone(),
           appointmentDTO.getDoctorId(),doctorDTO.getName(),
           appointmentDTO.getAppointmentDateTime(),appointmentDTO.getStatus(),
           appointmentDTO.getReason(),appointmentDTO.getNotes());
    }
    
}
