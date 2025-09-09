package com.hms.appointment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hms.appointment.dto.AppointmentDetailsDTO;
import com.hms.appointment.entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    @Query("SELECT new com.hms.appointment.dto.AppointmentDetailsDTO(a.id,a.patientId,null,null,null,a.doctorId, null , a.appointmentDateTime,a.status,a.reason,a.notes)FROM Appointment a WHERE a.patientId=?1")
    List<AppointmentDetailsDTO> findAllByPatientId(Long patientId);
    @Query("SELECT new com.hms.appointment.dto.AppointmentDetailsDTO(a.id,a.patientId,null,null,null,a.doctorId, null , a.appointmentDateTime,a.status,a.reason,a.notes)FROM Appointment a WHERE a.doctorId=?1")
    List<AppointmentDetailsDTO> findAllByDoctorId(Long doctorId);
}
