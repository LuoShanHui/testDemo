package com.qf.shop.eureka.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 *  eureka注册中心
 */
@EnableEurekaServer
@SpringBootApplication
public class ShopEurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopEurekaServerApplication.class, args);
    }

}
