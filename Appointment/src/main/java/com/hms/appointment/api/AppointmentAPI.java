package com.hms.appointment.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hms.appointment.dto.AppointmentDTO;
import com.hms.appointment.dto.AppointmentDetailsDTO;
import com.hms.appointment.exception.HmsException;
import com.hms.appointment.service.AppointmentService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;





@RestController
@RequestMapping("/appointment")
@Validated
@RequiredArgsConstructor
public class AppointmentAPI {
    private final AppointmentService appointmentService;
    
    @PostMapping("/schedule")
    public ResponseEntity<Long> scheduleAppointment(@RequestBody AppointmentDTO appointmentDTO) throws HmsException {
        //TODO: process PUT request
        return new ResponseEntity<>(appointmentService.scheduleAppointment(appointmentDTO),HttpStatus.CREATED);
    }
    @PutMapping("/cancel/{appointmentId}")
    public ResponseEntity<String> cancleAppointment(@PathVariable Long appointmentId) throws HmsException {
        appointmentService.cancelAppointment(appointmentId);
        return new ResponseEntity<>("Appointment Cancelled",HttpStatus.OK);
    }
    @GetMapping("/getAppointment/{appointmentId}")
    public ResponseEntity<AppointmentDTO> getMethodName(@PathVariable Long appointmentId) throws HmsException {
        AppointmentDTO appointmentDetails=appointmentService.getAppointmentDetails(appointmentId);
        return new ResponseEntity<>(appointmentDetails,HttpStatus.OK);
    }
    @GetMapping("get/details/{appointmentId}")
    public ResponseEntity<AppointmentDetailsDTO> getAppointmentDetailsWithName(@PathVariable Long  appointmentId) throws HmsException {
        return new ResponseEntity<>(appointmentService.getAppointmentDetailsWithName(appointmentId),HttpStatus.OK);
    }
    
    
}
