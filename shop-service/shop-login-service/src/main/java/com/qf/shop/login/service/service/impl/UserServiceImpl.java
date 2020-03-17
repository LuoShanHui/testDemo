package com.qf.shop.login.service.service.impl;

import com.qf.dto.ResultBean;
import com.qf.entity.TUser;
import com.qf.mapper.TUserMapper;
import com.qf.shop.login.service.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author Administrator
 * @PACKAGE myteam-shop
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private TUserMapper mapper;

    @Override
    public ResultBean checkLogin(String uname, String password) {
        TUser user = mapper.selectByUsername(uname);
        if (user!=null){
            if (user.getPassword()!=null&&password.equals(user.getPassword())){
                return ResultBean.success(user,"登录成功");
            }
        }
        return ResultBean.error("用户名或密码错误");
    }
}
