package com.hms.pharmacy.dto;

import java.time.LocalDate;

import com.hms.pharmacy.entity.Medicine;
import com.hms.pharmacy.entity.MedicineInventory;

import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicineInventoryDTO {
   private Long id;
    private Long  medicineId;
    private Integer quantity;
    private String batchNo;
     private LocalDate expiryDate;
    private LocalDate addedDate; 
    private Integer initialQuantity;
    private StockStatus status;
    public MedicineInventory toEntity() {
        return new MedicineInventory(id, new Medicine(medicineId), quantity, batchNo, expiryDate, addedDate,initialQuantity,status);
    }   
}
