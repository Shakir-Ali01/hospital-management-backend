package com.hms.pharmacy.service;

import java.util.List;

import com.hms.pharmacy.dto.MedicineInventoryDTO;
import com.hms.pharmacy.entity.MedicineInventory;
import com.hms.pharmacy.exception.HmsException;

public interface MedicineInventroyService {
    
    public List<MedicineInventoryDTO> getAllMedicine() throws HmsException;
    public MedicineInventoryDTO getMedicineById(Long id)throws HmsException;
    public MedicineInventoryDTO addMedicine(MedicineInventoryDTO medicineInventoryDTO)throws HmsException;
    public MedicineInventoryDTO updateMedicine(MedicineInventoryDTO mInventoryDTO)throws HmsException;

    void deleteMedicine(Long medicineId);
    public void deleteExpiredMedicines() throws HmsException;
        

}
