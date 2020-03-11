package com.qf.service;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "SHOPCAR-SERVICE")
public interface ShopcarService {
}
