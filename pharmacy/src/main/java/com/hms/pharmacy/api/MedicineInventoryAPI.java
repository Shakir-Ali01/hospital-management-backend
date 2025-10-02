package com.hms.pharmacy.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hms.pharmacy.dto.MedicineDTO;
import com.hms.pharmacy.dto.MedicineInventoryDTO;
import com.hms.pharmacy.dto.ResponseDTO;
import com.hms.pharmacy.exception.HmsException;
import com.hms.pharmacy.service.MedicineInventroyService;

import lombok.RequiredArgsConstructor;

@RestController
@Validated
@RequestMapping("/pharmacy/inventory/")
@RequiredArgsConstructor
public class MedicineInventoryAPI {
    private final MedicineInventroyService medicineInventroyService;

    @PostMapping("/addMedicine")
    public ResponseEntity<MedicineInventoryDTO> addMedicine(@RequestBody MedicineInventoryDTO medicineInventoryDTO) throws HmsException
    {
        return new ResponseEntity<>(medicineInventroyService.addMedicine(medicineInventoryDTO),HttpStatus.CREATED);
    }

    @GetMapping("/getMedicineById/{id}")
    public ResponseEntity<MedicineInventoryDTO> getMedicineById(@PathVariable Long id) throws HmsException {
        return new ResponseEntity<>(medicineInventroyService.getMedicineById(id), HttpStatus.OK);
    }

   @PutMapping("/update")
   public ResponseEntity<MedicineInventoryDTO> updateMedicine(@RequestBody MedicineInventoryDTO medicineInventoryDTO) throws HmsException {
       //TODO: process PUT request
       return new  ResponseEntity<>(medicineInventroyService.updateMedicine(medicineInventoryDTO),HttpStatus.OK);
   }
     
    @GetMapping("/getAllMedicine")
    public ResponseEntity<List<MedicineInventoryDTO>> getAllMedicine() throws HmsException {
        return new ResponseEntity<>(medicineInventroyService.getAllMedicine(),HttpStatus.OK);
    }
}
