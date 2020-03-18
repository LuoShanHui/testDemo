package com.qf.shop.index.service.service.impl;

import com.qf.entity.TProduct;
import com.qf.entity.TProductType;
import com.qf.mapper.TProductMapper;
import com.qf.mapper.TProductTypeMapper;
import com.qf.shop.index.service.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Administrator
 * @PACKAGE myteam-shop
 */
@Service
public class IndexServiceImpl implements IndexService {

    @Autowired
    private TProductTypeMapper productTypeMapper;

    @Autowired
    private TProductMapper productMapper;


    @Override
    public List<TProductType> selectAllProductType() {
        return productTypeMapper.selectAll();
    }

    @Override
    public List<TProduct> selectAllProduct(){
        return productMapper.selectAll();
    }


}
