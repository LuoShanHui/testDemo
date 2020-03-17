package com.qf.shop.login.service.controller;

import com.qf.dto.ResultBean;
import com.qf.shop.login.service.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Administrator
 * @PACKAGE myteam-shop
 */
@RestController
@RequestMapping("login")
public class LoginController {

    @Autowired
    private IUserService service;

    @RequestMapping("checkLogin")
    public ResultBean checkLogin( String uname, String password){
        return service.checkLogin(uname,password);
    }

    @RequestMapping("checkIsLogin")
    public ResultBean checkIsLogin(String uuid){
        return service.checkIsLogin(uuid);
    }
}
