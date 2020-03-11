package com.qf.service;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "SEARCH-SERVICE")
public interface SearchService {
}
