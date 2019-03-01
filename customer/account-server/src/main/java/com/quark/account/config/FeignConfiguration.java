package com.quark.account.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

@Configuration
public class FeignConfiguration{
    private RequestTemplate requestTemplate;

    @Bean
    public RequestInterceptor requestInterceptor(){
        return requestTemplate -> requestTemplate.header(HttpHeaders.AUTHORIZATION, "token");
    }
}