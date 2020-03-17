package com.qf.shop.regist.web.controller;

import com.qf.constant.RedisConstant;
import com.qf.dto.ResultBean;
import com.qf.shop.regist.web.service.IRegistService;
import com.qf.util.HttpClientUtils;
import com.qf.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Author Administrator
 * @PACKAGE myteam-shop
 */
@Controller
@RequestMapping("/user")
public class RegistController {

    @Autowired
    private IRegistService service;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("showRegister")
    public String showRegister(){
        return "register";
    }

    /**
     * 邮箱注册
     * @param email
     * @param password
     * @return
     */
    @RequestMapping("regist/email")
    public ResultBean registerByEmail(String email, String password) {
        //1.生成uuid
        String uuid = UUID.randomUUID().toString();
        //2.发邮件  通过ribbon来调用 (String.format:字符串格式化  %s:占位符 )
        String url = String.format("http://email-service/email/send/%s/%s", email, password);
        ResultBean resultBean = restTemplate.getForObject(url, ResultBean.class);
        ResultBean result = null;
        if (resultBean.getErrno() == 0) {
            //3.url组织好的键值对存到redis中
            //往redis中存入这样的键值对： regist:email:url:128731-sdfl-123 = addr
            redisTemplate.opsForValue().set(RedisUtil.getRedisKey(
                    RedisConstant.REGIST_EMAIL_URL_KEY_PRE, uuid), email, 15, TimeUnit.MINUTES);
            //4.去数据库中创建该用户,返回添加的数据的id
            result = restTemplate.getForObject(String.format(
                    "http://regist-service/user/regist/%s/%s", email, password), ResultBean.class);

            HttpClientUtils.doGet("");
        }

        return result;
    }

    /**
     * 注册用户信息
     *
     * @param uname    注册的用户名
     * @param password 注册密码
     * @return
     */
    @RequestMapping("/regist")
    public ResultBean register(@RequestParam String uname, @RequestParam String password) {
        return service.register(uname, password);
    }


}
