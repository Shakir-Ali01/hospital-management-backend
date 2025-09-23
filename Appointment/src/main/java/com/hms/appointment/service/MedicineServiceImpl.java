package com.hms.appointment.service;

import java.util.List;
import com.hms.appointment.entity.Medicine;
import org.springframework.stereotype.Service;

import com.hms.appointment.dto.MedicineDTO;
import com.hms.appointment.entity.Medicine;
import com.hms.appointment.exception.HmsException;
import com.hms.appointment.repository.MedicineRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;



@Service
@RequiredArgsConstructor
@Transactional
public class MedicineServiceImpl implements MedicineService {
    private final MedicineRepository medicineRepository;

    @Override
    public Long saveMedicine(MedicineDTO request) throws HmsException {
        // TODO Auto-generated method stub
        return medicineRepository.save(request.toEntity()).getId();
    }

    @Override
    public List<MedicineDTO> getMedicinesByPrescriptionId(Long prescriptionId) throws HmsException {
        // TODO Auto-generated method stub
        return medicineRepository.findAllByPrescription_Id(prescriptionId).stream().map(Medicine::toDTO).toList();
    }

    @Override
    public List<MedicineDTO> saveAllMedicines(List<MedicineDTO> medicineDTOs) throws HmsException {
        // TODO Auto-generated method stub
        List<Medicine> medicines=medicineDTOs.stream().map(MedicineDTO::toEntity).toList();
        List<Medicine> savedMedicines=medicineRepository.saveAll(medicines);    
        return savedMedicines.stream().map(Medicine::toDTO).toList();
                
}
}