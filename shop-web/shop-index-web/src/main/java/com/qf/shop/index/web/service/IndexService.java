package com.qf.shop.index.web.service;

import com.qf.dto.ResultBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author Administrator
 * @PACKAGE myteam-shop
 */
@FeignClient("INDEX-SERVICE")
public interface IndexService {

    @RequestMapping("indexForService/setIndex")
    ResultBean setIndex();
}
