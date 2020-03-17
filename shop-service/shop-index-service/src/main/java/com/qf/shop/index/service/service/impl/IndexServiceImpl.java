package com.qf.shop.index.service.service.impl;

import com.qf.entity.TProductType;
import com.qf.mapper.TProductTypeMapper;
import com.qf.shop.index.service.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Administrator
 * @PACKAGE myteam-shop
 */
@Service
public class IndexServiceImpl implements IndexService {

    @Autowired
    private TProductTypeMapper mapper;

    @Override
    public List<TProductType> selectAll() {
        return mapper.selectAll();
    }
}
