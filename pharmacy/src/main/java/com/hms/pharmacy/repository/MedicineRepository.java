package com.hms.pharmacy.repository;

import java.lang.foreign.Linker.Option;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.hms.pharmacy.entity.Medicine;

public interface MedicineRepository  extends JpaRepository<Medicine, Long> {

    Optional<Medicine> findByNameIgnoreCaseAndDosageIgnoreCase(String name, String dosage);
    
}
