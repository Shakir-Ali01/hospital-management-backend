package com.hms.pharmacy.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hms.pharmacy.dto.SaleItemDTO;
import com.hms.pharmacy.entity.SaleItem;
import com.hms.pharmacy.exception.HmsException;
import com.hms.pharmacy.repository.SaleItemRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class SaleItemServiceImpl implements SaleItemService {

    private final SaleItemRepository saleItemRepository;
    @Override
    public Long createSaleItem(SaleItemDTO saleItemDTO) throws HmsException {
        // TODO Auto-generated method stub
       return saleItemRepository.save(saleItemDTO.toEntity()).getId();
    }

    @Override
    public void createMultipleSaleItem(Long saleId, Long medicineId, List<SaleItemDTO> saleItemDTOs)
            throws HmsException {
        // TODO Auto-generated method stub
        saleItemDTOs.stream().map((x)->{
        x.setSaleId(saleId);
        x.setMedicineId(medicineId);
        return x.toEntity();
            }).forEach(saleItemRepository::save);
    }

    @Override
    public void updateSaleItem(SaleItemDTO saleItemDTO) throws HmsException {
        // TODO Auto-generated method stub
        SaleItem existingSaleItem=saleItemRepository.findById(saleItemDTO.getId()).orElseThrow(()-> new HmsException("SALE_ITEM_NOT_FOUND"));
        existingSaleItem.setUnitPrice(saleItemDTO.getUnitPrice());
        existingSaleItem.setQuantity(saleItemDTO.getQuantity());
        saleItemRepository.save(existingSaleItem);
    }

    @Override
    public List<SaleItemDTO> getSaleItemBySaleId(Long SaleId) throws HmsException {
        return saleItemRepository.findBySaleId(SaleId).stream().map(SaleItem::toDTO).toList();
    }

    @Override
    public SaleItemDTO getSaleItem(Long id) throws HmsException {
        // TODO Auto-generated method stub
        return saleItemRepository.findById(id).map(SaleItem::toDTO).orElseThrow(()->new HmsException("SALE_NOT_FOUND"));
    }
    
}
