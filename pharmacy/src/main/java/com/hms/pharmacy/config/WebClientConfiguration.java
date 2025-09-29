package com.hms.pharmacy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Configuration
public class WebClientConfiguration {
       /* 
       Key Difference
        If you inject WebClient directly → you always get the same client (same base URL, same config).
        If you inject WebClient.Builder → you can create multiple clients with different configs as needed.
       */ 
    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder().defaultHeader(("X-Secret-Key"), "SECRET").filter(logRequest());
    }
    //This method is used to log the request details whenver a request is made using the aobve WebClient.
    // This is useful for debugging and monitoring purposes.
    private ExchangeFilterFunction logRequest() {
        return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
            System.out.println("Request: " + clientRequest.method() + " " + clientRequest.url());
            // clientRequest.headers()
            //         .forEach((name, values) -> values.forEach(value -> System.out.println(name + ": " + value)));
            return Mono.just(clientRequest);
        });
    }
}
