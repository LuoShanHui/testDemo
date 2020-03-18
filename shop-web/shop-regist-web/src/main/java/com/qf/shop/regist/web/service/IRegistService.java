package com.qf.shop.regist.web.service;

import com.qf.dto.ResultBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author Administrator
 * @PACKAGE myteam-shop
 */
@FeignClient("regist-service")
public interface IRegistService {

    @RequestMapping("user/regist")
    ResultBean register(@RequestParam String uname, @RequestParam String password);
}
