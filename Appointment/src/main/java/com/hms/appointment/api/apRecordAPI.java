package com.hms.appointment.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hms.appointment.dto.ApRecordDTO;
import com.hms.appointment.dto.PrescriptionDetailsDTO;
import com.hms.appointment.dto.RecordDetailsDTO;
import com.hms.appointment.exception.HmsException;
import com.hms.appointment.service.ApRecordService;
import com.hms.appointment.service.PrescriptionService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

 

@RestController
@Validated
@RequestMapping("/appointment/report")
@RequiredArgsConstructor
public class apRecordAPI {
     private final ApRecordService apRecordService;
     private final PrescriptionService  prescriptionService;
    @PostMapping("/create")
    public ResponseEntity<Long> createApRecord(@RequestBody ApRecordDTO request) throws HmsException {
        return new ResponseEntity<>(apRecordService.createApRecord(request), HttpStatus.CREATED);
    }
    @PutMapping("/update")
    public ResponseEntity<String> updateApRecord(@RequestBody ApRecordDTO request) throws HmsException {
        apRecordService.updateApRecord(request);
        return new ResponseEntity<>("Appointment Report updated. ",HttpStatus.OK);
    }
    @GetMapping("/getByAppointmentId/{appointmentId}")
    public ResponseEntity<ApRecordDTO> getApRecordByAppointmentId(@PathVariable Long appointmentId) throws HmsException {
        return new ResponseEntity<>(apRecordService.getApRecordByAppointmentId(appointmentId),HttpStatus.OK);
    }
     @GetMapping("/getDetailsByAppointmentId/{appointmentId}")
    public ResponseEntity<ApRecordDTO> getApRecordDetailsByAppointmentId(@PathVariable Long appointmentId) throws HmsException {
        return new ResponseEntity<>(apRecordService.getApRecordDetailsByAppointmentId(appointmentId),HttpStatus.OK);
    }
    @GetMapping("/getById/{recordId}")
    public ResponseEntity<ApRecordDTO> getApRecordById(@PathVariable Long id) throws HmsException {
        return new ResponseEntity<>(apRecordService.getApRecordById(id),HttpStatus.OK);
    }
    @GetMapping("/getRecordsByPatientId/{patientId}")
    public ResponseEntity<List<RecordDetailsDTO>> getApRecordsByPatientId(@PathVariable Long patientId) throws HmsException {
        return new ResponseEntity<>(apRecordService.getApRecordsByPatientId(patientId),HttpStatus.OK);
    }
    @GetMapping("/isRecordExists/{appointmentId}")
    public ResponseEntity<Boolean> isAppointmentRecordExists(@PathVariable Long appointmentId) throws HmsException {
        return new ResponseEntity<>(apRecordService.isAppointmentRecordExists(appointmentId),HttpStatus.OK);
    }
    @GetMapping("/getPrescriptionsByPatientId/{patientId}")
    public ResponseEntity<List<PrescriptionDetailsDTO>> getMethodName(@PathVariable Long patientId) throws HmsException {
        // TODO Auto-generated method stub{
        return new ResponseEntity<>(prescriptionService.getPrescriptionsByPatientId(patientId),HttpStatus.OK);
    }
    

}
