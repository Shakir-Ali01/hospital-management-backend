package com.hms.pharmacy.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hms.pharmacy.dto.MedicineDTO;
import com.hms.pharmacy.dto.ResponseDTO;
import com.hms.pharmacy.exception.HmsException;
import com.hms.pharmacy.service.MedicineService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@Validated
@RequestMapping("/pharmacy/medicines")
@RequiredArgsConstructor
public class MedicineAPI {
    private final MedicineService medicineService;

    @PostMapping("/addMedicine")
    public ResponseEntity<Long> addMedicine(@RequestBody @Valid MedicineDTO medicineDTO) throws HmsException {
        return new ResponseEntity<>(medicineService.addMedicine(medicineDTO), HttpStatus.CREATED);
    }
    
    @GetMapping("/getMedicineById/{id}")
    public ResponseEntity<MedicineDTO> getMedicineById(@PathVariable Long id) throws HmsException {
        return new ResponseEntity<>(medicineService.getMedicineById(id), HttpStatus.OK);
    }

   @PutMapping("/update")
   public ResponseEntity<ResponseDTO> updateMedicine(@RequestBody MedicineDTO medicineDTO) throws HmsException {
       //TODO: process PUT request
       medicineService.updateMedicine(medicineDTO);
       return new  ResponseEntity<>(new ResponseDTO("Medicine Updated Successfully"),HttpStatus.OK);
   }
     
    @GetMapping("/getAllMedicine")
    public ResponseEntity<List<MedicineDTO>> getAllMedicine() throws HmsException {
        return new ResponseEntity<>(medicineService.getAllMedicines(),HttpStatus.OK);
    }
    
    
}
