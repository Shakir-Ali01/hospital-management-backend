package com.hms.pharmacy.entity;

import java.time.LocalDateTime;

import com.hms.pharmacy.dto.SaleDTO;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sale {
     @Id
     @GeneratedValue(strategy=GenerationType.IDENTITY)
     private Long id;
     private Long priscriptionId;
     private LocalDateTime saleDate;
     private Double totalAmount;


     public Sale(Long id){
       this.id=id;
     }

     public SaleDTO toDTO(){
        return new SaleDTO(id,priscriptionId,saleDate,totalAmount);
     }

}
