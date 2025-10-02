package com.hms.pharmacy.api;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hms.pharmacy.dto.ResponseDTO;
import com.hms.pharmacy.dto.SaleDTO;
import com.hms.pharmacy.dto.SaleItemDTO;
import com.hms.pharmacy.exception.HmsException;
import com.hms.pharmacy.service.SaleItemService;
import com.hms.pharmacy.service.SaleService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@Validated
@RequestMapping("/pharmacy/sales")
@RequiredArgsConstructor
public class SaleAPI {
    private final SaleItemService saleItemService;
    private final SaleService saleService;

    @PostMapping("/create")
    public ResponseEntity<Long>  createSale(@RequestBody SaleDTO saleDTO)  throws HmsException{
        //TODO: process POST request
        return new ResponseEntity<>(saleService.createSale(saleDTO),HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDTO> updateSale( @RequestBody SaleDTO saleDTO) throws HmsException {
        //TODO: process PUT request
        
         saleService.updateSale(saleDTO);
        return new ResponseEntity<>(new ResponseDTO("sale Updated Successfully"),HttpStatus.OK);
    }

    @GetMapping("/getSaleItems/{saleId}")
    public ResponseEntity<List<SaleItemDTO>> getSaleBySaleId(@PathVariable Long saleId) throws HmsException {
        return new ResponseEntity<>(saleItemService.getSaleItemBySaleId(saleId),HttpStatus.OK);
    }
    
    @GetMapping("/getById/{id}")
    public ResponseEntity<SaleDTO> getSaleById(@PathVariable Long id) throws HmsException {
        return new ResponseEntity<>(saleService.getSale(id),HttpStatus.OK);
    }
    
    
}
