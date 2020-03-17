package com.qf.shop.regist.service.service;

import com.qf.dto.ResultBean;

/**
 * @Author Administrator
 * @PACKAGE myteam-shop
 */
public interface IRegistService {

    ResultBean regist(String addr, String password);

    ResultBean updateFlag(String uname);
}
