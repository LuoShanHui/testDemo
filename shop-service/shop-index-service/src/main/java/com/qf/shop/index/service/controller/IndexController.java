package com.qf.shop.index.service.controller;

import com.qf.constant.RedisConstant;
import com.qf.dto.ResultBean;
import com.qf.entity.TProductType;
import com.qf.shop.index.service.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author Administrator
 * @PACKAGE myteam-shop
 */
@RestController
@RequestMapping("indexForService")
public class IndexController {

    @Autowired
    private IndexService service;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("setIndex")
    public ResultBean setIndex(){
        List<TProductType> productTypeList=service.selectAll();
        //将查到的商品列别集合放到redis上
        //redisTemplate.opsForValue().set(RedisConstant.INDEX_SET_PRODUCT_TYPE, productTypeList, 15,TimeUnit.MINUTES);
        //清空redis中改key值对应的数据
        while (redisTemplate.opsForList().size(RedisConstant.INDEX_SET_PRODUCT_TYPE)>0){
            redisTemplate.opsForList().leftPop(RedisConstant.INDEX_SET_PRODUCT_TYPE);
        }
        //将查到的商品列别集合放到redis上
        redisTemplate.opsForList().rightPush(RedisConstant.INDEX_SET_PRODUCT_TYPE, productTypeList);
        return ResultBean.success();
    }
}
