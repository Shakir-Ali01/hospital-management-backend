package com.hms.pharmacy.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hms.pharmacy.entity.MedicineInventory;

public interface MedicineInventoryRepository extends JpaRepository <MedicineInventory, Long> {
    List<MedicineInventory> findByExpiryDateBefore(LocalDate date);
}
