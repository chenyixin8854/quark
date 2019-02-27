package com.quark.operate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class OperateApplication {
    public static void main(String[] args) {
        SpringApplication.run( OperateApplication.class, args );
    }
}
