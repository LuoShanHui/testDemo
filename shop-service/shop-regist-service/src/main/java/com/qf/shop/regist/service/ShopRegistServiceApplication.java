package com.qf.shop.regist.service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.qf.mapper")
public class ShopRegistServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopRegistServiceApplication.class, args);
    }

}
