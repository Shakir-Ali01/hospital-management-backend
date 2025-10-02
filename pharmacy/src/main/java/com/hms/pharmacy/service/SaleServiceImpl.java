package com.hms.pharmacy.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.hms.pharmacy.dto.SaleDTO;
import com.hms.pharmacy.entity.Sale;
import com.hms.pharmacy.exception.HmsException;
import com.hms.pharmacy.repository.SaleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;

    @Override
    public Long createSale(SaleDTO saledto) throws HmsException {
        // TODO Auto-generated method stub
        if(saleRepository.existByPrescriptionId(saledto.getPriscriptionId())){
         throw  new HmsException("SALE_ALREADY_EXISTS");
       }
        saledto.setSaleDate(LocalDateTime.now());

        return saleRepository.save(saledto.toEntity()).getId();
    }

    @Override
    public void updateSale(SaleDTO saleDTO) throws HmsException {
        // TODO Auto-generated method stub
      Sale sale=saleRepository.findById(saleDTO.getId()).orElseThrow(()->new HmsException("SALE_NOT_FOUND"));
           sale.setSaleDate(saleDTO.getSaleDate());
           sale.setTotalAmount(saleDTO.getTotalAmount());
           saleRepository.save(sale);

    }

    @Override
    public SaleDTO getSale(Long id) throws HmsException {
        // TODO Auto-generated method stub
        return saleRepository.findById(id).orElseThrow(()-> new HmsException("SALE_NOT_FOUND")).toDTO();
    }

    @Override
    public SaleDTO getSelByPrescriptionId(Long prescriptionId) throws HmsException {
        // TODO Auto-generated method stub
        return saleRepository.findByPrescriptionId(prescriptionId).orElseThrow(()-> new HmsException("SALE_NOT_FOUND")).toDTO();
    }
    
}
