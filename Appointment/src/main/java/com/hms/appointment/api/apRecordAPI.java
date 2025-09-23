package com.hms.appointment.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hms.appointment.dto.ApRecordDTO;
import com.hms.appointment.exception.HmsException;
import com.hms.appointment.service.ApRecordService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@CrossOrigin
@Validated
@RequestMapping("/appointment/report")

@RequiredArgsConstructor
public class apRecordAPI {
     private final ApRecordService apRecordService;
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

}
