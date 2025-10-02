package com.hms.pharmacy.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.hms.pharmacy.dto.MedicineInventoryDTO;
import com.hms.pharmacy.dto.StockStatus;
import com.hms.pharmacy.entity.MedicineInventory;
import com.hms.pharmacy.exception.HmsException;
import com.hms.pharmacy.repository.MedicineInventoryRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MedicineInventoryServiceImpl  implements MedicineInventroyService{
    private final MedicineInventoryRepository medicineInventoryRepository;
    private final MedicineService medicineService;


    @Override
    public List<MedicineInventoryDTO> getAllMedicine() throws HmsException {
        // TODO Auto-generated method stub
        List<MedicineInventory> inventories=(List<MedicineInventory>)medicineInventoryRepository.findAll();
        return inventories.stream()
        .map(MedicineInventory::toDTO)
        .toList();
    }

    @Override
    public MedicineInventoryDTO getMedicineById(Long id) throws HmsException {
        // TODO Auto-generated method stub
       return medicineInventoryRepository.findById(id).orElseThrow(()-> new HmsException("INVENTORY_NOT_FOUND")).toDTO();
    }

    @Override
    public MedicineInventoryDTO addMedicine(MedicineInventoryDTO medicineInventoryDTO) throws HmsException {
        // TODO Auto-generated method stub
       medicineInventoryDTO.setAddedDate(LocalDate.now());
       medicineService.addStock(medicineInventoryDTO.getMedicineId(), medicineInventoryDTO.getQuantity());
       medicineInventoryDTO.setInitialQuantity(medicineInventoryDTO.getQuantity());
       medicineInventoryDTO.setStatus(StockStatus.ACTIVE);
       return medicineInventoryRepository.save(medicineInventoryDTO.toEntity()).toDTO();

    }

    @Override
    public MedicineInventoryDTO updateMedicine(MedicineInventoryDTO mInventoryDTO) throws HmsException {
        // TODO Auto-generated method stub
       MedicineInventory existingInventory=medicineInventoryRepository.findById(mInventoryDTO.getId()).orElseThrow(()->new HmsException("INVENTORY_NOT_FOUND"));
       if(existingInventory.getQuantity()< mInventoryDTO.getQuantity()){
          medicineService.addStock(mInventoryDTO.getMedicineId(), mInventoryDTO.getQuantity()-existingInventory.getQuantity());
       }else if(existingInventory.getQuantity() > mInventoryDTO.getQuantity()){
              medicineService.removeStock(mInventoryDTO.getMedicineId(), existingInventory.getQuantity()- mInventoryDTO.getQuantity());
       }
       existingInventory.setQuantity(mInventoryDTO.getQuantity());
       existingInventory.setBatchNo(mInventoryDTO.getBatchNo());
       existingInventory.setInitialQuantity(mInventoryDTO.getInitialQuantity());
       existingInventory.setExpiryDate(mInventoryDTO.getExpiryDate());
       return medicineInventoryRepository.save(existingInventory).toDTO();
    }

    @Override
    public void deleteMedicine(Long medicineId) {
        // TODO Auto-generated method stub
        medicineInventoryRepository.deleteById(medicineId);
    }   
    private void markedExpired(List<MedicineInventory> inventories) throws HmsException{
      for(MedicineInventory inventory:inventories){
            inventory.setStatus(StockStatus.EXPIRED);
      }
     medicineInventoryRepository.saveAll(inventories);
    }
    @Override 
    @Scheduled(cron="0 56 12 * * ?")
    public void deleteExpiredMedicines() throws HmsException{
       List<MedicineInventory> expiredMedicines =medicineInventoryRepository.findByExpiryDateBefore(LocalDate.now());
       for(MedicineInventory medicine : expiredMedicines){
           medicineService.removeStock(medicine.getMedicine().getId(),medicine.getQuantity());
       }
       this.markedExpired(expiredMedicines);
        
    }
    // @Scheduled(fixedRate = 5000)
    // public void print(){
    //     System.out.println("Scheduled task is Running");
    // }
    @Scheduled(cron="0 15 12 * * ?") //at 14:30
    public void print1(){
        System.out.println("Scheduled task running ...");
    }
    
}
