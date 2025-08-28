package com.hms.appointment.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.hms.appointment.dto.DoctorDTO;
import com.hms.appointment.dto.PatientDTO;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ApiService {
    /* 
        Key Difference
        If you inject WebClient directly → you always get the same client (same base URL, same config).
        If you inject WebClient.Builder → you can create multiple clients with different configs as needed.
    */
    private final WebClient.Builder webClientBuilder;

    public Mono<Boolean> doctorExists(Long id){
           return webClientBuilder.build()
                .get()
                .uri("http://localhost:9100/profile/doctors/exists/"+id)
                .retrieve()
                .bodyToMono(Boolean.class);
    }
    public Mono<Boolean> patientExists(Long id){
           return webClientBuilder.build()
                .get()
                .uri("http://localhost:9100/profile/patient/exists/" +id)
                .retrieve()
                .bodyToMono(Boolean.class);
    }
   public Mono<PatientDTO> getPatientById(Long id){
    return webClientBuilder.build()
                .get()
                .uri("http://localhost:9100/profile/patient/get/" +id)
                .retrieve()
                .bodyToMono(PatientDTO.class);
    }

      public Mono<DoctorDTO> getDoctorById(Long id){
    return webClientBuilder.build()
                .get()
                .uri("http://localhost:9100/profile/doctors/get/" +id)
                .retrieve()
                .bodyToMono(DoctorDTO.class);
    }
   
}
