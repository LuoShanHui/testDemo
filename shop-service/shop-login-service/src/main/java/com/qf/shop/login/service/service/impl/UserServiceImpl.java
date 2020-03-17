package com.qf.shop.login.service.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qf.constant.RedisConstant;
import com.qf.dto.ResultBean;
import com.qf.entity.TUser;
import com.qf.mapper.TUserMapper;
import com.qf.shop.login.service.service.IUserService;
import com.qf.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @Author Administrator
 * @PACKAGE myteam-shop
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private TUserMapper mapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public ResultBean checkLogin(String uname, String password) {
        TUser user = mapper.selectByUsername(uname);
        if (user != null) {
            if (user.getPassword() != null && password.equals(user.getPassword())) {
                return ResultBean.success(user, "登录成功");
            }
        }
        return ResultBean.error("用户名或密码错误");
    }

    @Override
    public ResultBean checkIsLogin(String uuid) {
        if (uuid != null && !"".equals(uuid)) {
            //1.组织键   user:login:dbe06afc-8540-4b74-8035-99e188d33933
            String redisKey = RedisUtil.getRedisKey(RedisConstant.USER_LOGIN_PRE, uuid);
            //2.去redis查询
            Object o = redisTemplate.opsForValue().get(redisKey);
            if (o != null) {
                ObjectMapper objectMapper=new ObjectMapper();

                try {
                    String json=objectMapper.writeValueAsString(o);
                    TUser user=objectMapper.readValue(json, TUser.class);
                    user.setPassword("");

                    return ResultBean.success(user, "用户已登录!");

                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }

            }
        }
        return ResultBean.error("用户未登录!");
    }
}
