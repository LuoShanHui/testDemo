package com.qf.shop.login.web.service;

import com.qf.dto.ResultBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author Administrator
 * @PACKAGE myteam-shop
 */
@FeignClient("LOGIN-SERVICE")
public interface IUserService {

    @RequestMapping("login/checkLogin")
    ResultBean checkLogin(@RequestParam String uname, @RequestParam String password);


    @RequestMapping("login/checkIsLogin")
    ResultBean checkIsLogin(@RequestParam String uuid);
}
