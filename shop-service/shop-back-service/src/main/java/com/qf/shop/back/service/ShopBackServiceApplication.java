package com.qf.shop.back.service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.qf.mapper")
public class ShopBackServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopBackServiceApplication.class, args);
    }

}
