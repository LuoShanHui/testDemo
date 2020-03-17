package com.qf.shop.email.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ShopEmailServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopEmailServiceApplication.class, args);
    }

}
