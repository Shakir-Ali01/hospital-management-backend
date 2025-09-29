package com.hms.pharmacy.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import com.hms.pharmacy.dto.MedicineDTO;
import com.hms.pharmacy.entity.Medicine;
import com.hms.pharmacy.exception.HmsException;
import com.hms.pharmacy.repository.MedicineRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MedicineServiceImpl implements MedicineService {
    private final MedicineRepository medicineRepository;

    @Override
    public Long addMedicine(MedicineDTO medicineDTO) throws HmsException {
        // TODO Auto-generated method stub
       Optional<Medicine> existingMedicine = medicineRepository.findByNameIgnoreCaseAndDosageIgnoreCase(medicineDTO.getName(), medicineDTO.getDosage());
         if (existingMedicine.isPresent()) {
              throw new HmsException("MEDICINE_ALREADY_EXISTS.");
         }
           medicineDTO.setCreatedAt(LocalDateTime.now());
           medicineRepository.save(medicineDTO.toEntity());
           return medicineDTO.getId();
    }

    @Override
    public MedicineDTO getMedicineById(Long id) throws HmsException {
        // TODO Auto-generated method stub
        medicineRepository.findById(id).orElseThrow(() -> new HmsException("MEDICINE_NOT_FOUND"));
        return medicineRepository.findById(id).get().toDTO();
        
    }

    @Override
    public void updateMedicine(MedicineDTO medicineDTO) throws HmsException {
        // TODO Auto-generated method stub
        Medicine existingMedicine=medicineRepository.findById(medicineDTO.getId()).orElseThrow(() -> new HmsException("MEDICINE_NOT_FOUND"));
        if(!medicineDTO.getName().equalsIgnoreCase(existingMedicine.getName()) || !medicineDTO.getDosage().equalsIgnoreCase(existingMedicine.getDosage())){
            //we are changing name or dosage, check for duplicates
            Optional<Medicine> medicineWithSameNameAndDosage = medicineRepository.findByNameIgnoreCaseAndDosageIgnoreCase(medicineDTO.getName(), medicineDTO.getDosage());
            if (medicineWithSameNameAndDosage.isPresent()) {
                throw new HmsException("MEDICINE_ALREADY_EXISTS.");
            }
        }
        existingMedicine.setName(medicineDTO.getName());
        existingMedicine.setDosage(medicineDTO.getDosage());
        existingMedicine.setCategory(medicineDTO.getCategory());
        existingMedicine.setType(medicineDTO.getType());
        existingMedicine.setManufacturer(medicineDTO.getManufacturer());
        existingMedicine.setUnitPrice(medicineDTO.getUnitPrice());
       
        medicineRepository.save(existingMedicine);
        return;
    }

    @Override
    public List<MedicineDTO> getAllMedicines() throws HmsException {
        // TODO Auto-generated method stub
       return  medicineRepository.findAll().stream().map(Medicine::toDTO).toList();
      //if our repo extends CrudRepository then we will use below code because findAll() returns Iterable
       
       /* return  ((List<Medicine>)medicineRepository.findAll()). we are converting iterable to list
          stream().map(Medicine::toDTO).toList();*/
      
    }
    

}
