package com.qf.shop.index.web.controller;

import com.qf.constant.RedisConstant;
import com.qf.dto.ResultBean;
import com.qf.entity.TProductType;
import com.qf.shop.index.web.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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

    public ResultBean checkIsLogin(){
        return  null;
    }

}
