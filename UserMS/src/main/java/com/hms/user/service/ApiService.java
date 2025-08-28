package com.hms.user.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.hms.user.dto.Roles;
import com.hms.user.dto.UserDTO;

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

    public Mono<Long> addProfile(UserDTO userDTO){
        if(userDTO.getRole().equals(Roles.DOCTOR)){
           return webClientBuilder.build()
                .post()
                .uri("http://localhost:9100/profile/doctors/create")
                .bodyValue(userDTO)
                .retrieve()
                .bodyToMono(Long.class);
        }else if(userDTO.getRole().equals(Roles.PATIENT)){
            return webClientBuilder.build()
                    .post()
                    .uri("http://localhost:9100/profile/patient/create")
                    .bodyValue(userDTO)
                    .retrieve()
                    .bodyToMono(Long.class);
        }return null;
    }
}
