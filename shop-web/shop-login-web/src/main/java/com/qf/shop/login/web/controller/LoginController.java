package com.qf.shop.login.web.controller;

import com.netflix.ribbon.proxy.annotation.Http;
import com.qf.constant.RedisConstant;
import com.qf.dto.ResultBean;
import com.qf.shop.login.web.service.IUserService;
import com.qf.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Author Administrator
 * @PACKAGE myteam-shop
 */
@Controller
@RequestMapping("user")
public class LoginController {

    @Autowired
    private IUserService service;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("showLogin")
    public String showLogin() {
        return "login";
    }

    @RequestMapping("checkLogin")
    public String checkLogin(String uname, String password, HttpServletResponse response) {
        //交给service去数据库验证用户名和密码是否正确
        ResultBean resultBean = service.checkLogin(uname, password);
        if(resultBean.getErrno()==0){
            //登录成功  保存cookie
            //生成cookie
            String uuid= UUID.randomUUID().toString();
            Cookie cookie=new Cookie("user_login", uuid);
            //将已登录信息保存到redis中
            //组织key
            String key= RedisUtil.getRedisKey(RedisConstant.USER_LOGIN_PRE, uuid);
            //把登录成功后的用户对象存入到redis中。以便checkIsLogin接口去判断是否已登录 来使用
            redisTemplate.opsForValue().set(key, resultBean.getData(), 30, TimeUnit.DAYS);
            //将cookie发送给客户端
            cookie.setMaxAge(30*24*60*60);
            cookie.setPath("/");
            cookie.setHttpOnly(true);
            response.addCookie(cookie);

            return "redirect:http://localhost:7760";
        }
        return "redirect:showLogin";
    }

    @RequestMapping("checkIsLogin")
    @ResponseBody
    public ResultBean checkIsLogin(@CookieValue(name = "user_login",required = false) String uuid){
        return service.checkIsLogin(uuid);
    }

}
