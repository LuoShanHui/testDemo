package com.qf.shop.regist.service.controller;

import com.qf.dto.ResultBean;
import com.qf.shop.regist.service.service.IRegistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Administrator
 * @PACKAGE myteam-shop
 */
@RestController
@RequestMapping("user")
public class RegistController {

    @Autowired
    private IRegistService service;

    @RequestMapping("regist/{addr}/{password}")
    public ResultBean register(@PathVariable String addr, @PathVariable String password){
        return service.regist(addr, password);
    }

    @RequestMapping("update-flag/{addr}")
    public ResultBean updateFlag(@PathVariable String addr){
        return service.updateFlag(addr);
    }
}
