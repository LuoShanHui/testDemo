package com.qf.shop.index.web.controller;

import com.google.gson.Gson;
import com.qf.constant.RedisConstant;
import com.qf.dto.ResultBean;
import com.qf.entity.TProductType;
import com.qf.shop.index.web.service.IndexService;
import com.qf.util.HttpClientUtils;
import com.qf.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Administrator
 * @PACKAGE myteam-shop
 */
@Controller
public class IndexController {

    @Autowired
    private IndexService service;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping({"","index"})
    public String showIndex(Model model){
        ResultBean resultBean = service.setIndex();
        List<TProductType> productTypes=new ArrayList<>();
        if(resultBean.getErrno()==0){
         productTypes=redisTemplate.opsForList().range(RedisConstant.INDEX_SET_PRODUCT_TYPE, 0, -1);
        }
        model.addAttribute("types",productTypes.get(0) );
        return "index";
    }

    /**
     * 检查是否已登录
     * @param uuid
     * @return
     */
    @RequestMapping("checkIsLogin")
    @ResponseBody
    public ResultBean checkIsLogin(@CookieValue(name = "user_login",required = false) String uuid){
        //携带cookie访问登录的web层,检查是否已登录
        String url="http://localhost:7764/user/checkIsLogin";

        //生成cookie: user_login=uuid
        String cookie=new StringBuilder().append("user_login").append("=").append(uuid).toString();
        //借用工具类向目标发送带cookie的请求,返回json数据结果
        String result = HttpClientUtils.doGet(url, cookie);

        //把result字符串封装成ResultBean对象,要引入gson依赖
        Gson gson=new Gson();
        ResultBean resultBean = gson.fromJson(result, ResultBean.class);

        return  resultBean;
    }

}
