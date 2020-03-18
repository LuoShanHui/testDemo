package com.qf.shop.back.web.controller;

import com.qf.dto.ResultBean;
import com.qf.entity.TProduct;
import com.qf.shop.back.web.service.IBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Administrator
 * @PACKAGE myteam-shop
 */
@RestController
@RequestMapping("back")
public class BackController {

    @Autowired
    private IBackService service;

    @RequestMapping("selectAllProduct")
    public ResultBean selectAllProduct() {
        return service.selectAllProduct();
    }

    @RequestMapping("add")
    public ResultBean addProduct(TProduct product) {
        return service.addProduct(product);
    }

    @RequestMapping("updateProduct")
    public ResultBean updateProduct(TProduct product) {
        return service.updateProduct(product);
    }

    @RequestMapping("deleteProduct")
    public ResultBean deleteProduct(Long id) {
        return service.deleteProduct(id);
    }
}
