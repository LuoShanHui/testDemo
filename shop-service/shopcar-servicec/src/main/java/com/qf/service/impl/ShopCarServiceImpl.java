package com.qf.service.impl;

import com.qf.dto.ResultBean;
import com.qf.mapper.TProductMapper;
import com.qf.service.ShopCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

public class ShopCarServiceImpl implements ShopCarService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private TProductMapper productMapper;
    @Override
    public ResultBean addProduct(String uuid, Long productId, int count) {
        return null;
    }

    @Override
    public ResultBean clean(String uuid) {
        return null;
    }

    @Override
    public ResultBean update(String uuid, Long productId, int count) {
        return null;
    }

    @Override
    public ResultBean showCart(String uuid) {
        return null;
    }
}
