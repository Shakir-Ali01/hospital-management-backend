package com.hms.pharmacy.service;

import com.hms.pharmacy.dto.SaleDTO;
import com.hms.pharmacy.exception.HmsException;

public interface SaleService {
    Long  createSale(SaleDTO saledto) throws HmsException;
    void updateSale(SaleDTO saleDTO) throws HmsException;
    public SaleDTO getSale(Long id) throws HmsException;
    public SaleDTO getSelByPrescriptionId(Long prescriptionId) throws HmsException;
    
}
