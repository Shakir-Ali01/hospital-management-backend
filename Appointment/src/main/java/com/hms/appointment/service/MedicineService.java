package com.hms.appointment.service;

import java.util.List;

import com.hms.appointment.dto.MedicineDTO;
import com.hms.appointment.exception.HmsException;

public interface MedicineService {
    public Long saveMedicine(MedicineDTO request) throws HmsException;
    public List<MedicineDTO> saveAllMedicines(List<MedicineDTO> medicines) throws HmsException;
    public List<MedicineDTO> getMedicinesByPrescriptionId(Long prescriptionId) throws HmsException;
    // public void deleteMedicinesByPrescriptionId(Long prescriptionId) throws HmsException;

}
