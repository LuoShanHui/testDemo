package com.qf.shop.regist.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ShopRegistWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopRegistWebApplication.class, args);
    }

}
