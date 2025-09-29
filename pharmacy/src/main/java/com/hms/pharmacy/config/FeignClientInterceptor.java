package com.hms.pharmacy.config;

import org.springframework.context.annotation.Configuration;

import feign.RequestInterceptor;
import feign.RequestTemplate;

@Configuration
public class FeignClientInterceptor implements RequestInterceptor{
    @Override
    public void apply(RequestTemplate template) {
        // TODO Auto-generated method stub
        template.header("X-Secret-Key", "SECRET");
    }
    
}
