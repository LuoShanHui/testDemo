package com.qf.shop.back.service.service;

import com.qf.dto.ResultBean;
import com.qf.entity.TProduct;

import java.util.List;

/**
 * @Author Administrator
 * @PACKAGE myteam-shop
 */
public interface IBackService {


    ResultBean addProduct(TProduct product);

    List<TProduct> selectAllProduct();

    ResultBean updateProduct(TProduct product);

    ResultBean deleteProduct(Long id);
}
