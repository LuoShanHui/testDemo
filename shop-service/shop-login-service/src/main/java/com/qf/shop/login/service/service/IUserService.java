package com.qf.shop.login.service.service;

import com.qf.dto.ResultBean;

/**
 * @Author Administrator
 * @PACKAGE myteam-shop
 */
public interface IUserService {
    ResultBean checkLogin(String uname, String password);
}
