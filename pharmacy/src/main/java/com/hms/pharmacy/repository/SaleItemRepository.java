package com.hms.pharmacy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hms.pharmacy.entity.SaleItem;

public interface SaleItemRepository  extends JpaRepository<SaleItem,Long>{
List<SaleItem> findBySaleId(Long saleId);
List<SaleItem> findByMedicineID(Long medicineId);
    
} 
