package com.hms.pharmacy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hms.pharmacy.entity.Sale;

public interface  SaleRepository extends JpaRepository<Sale,Long>{
     Boolean existByPrescriptionId(Long preseciptionId);
     Optional<Sale> findByPrescriptionId(Long prescriptionId);
}
