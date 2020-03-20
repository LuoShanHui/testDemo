package com.qf.shop.back.web.controller;

import com.qf.dto.ResultBean;
import com.qf.entity.TProduct;
import com.qf.shop.back.web.service.IBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Author Administrator
 * @PACKAGE myteam-shop
 */
@Controller
@RequestMapping("back")
public class BackController {

    @Autowired
    private IBackService service;

    @RequestMapping("index")
    public String goBack(){
        return "backIndex";
    }

    @RequestMapping("selectAllProduct")
    public String selectAllProduct(Model model) {
        ResultBean resultBean = service.selectAllProduct();
        List<TProduct> productList = (List<TProduct>) resultBean.getData();
        model.addAttribute("productList",productList);
        return "productList";
    }

    @RequestMapping("toAddProduct")
    public String toAddProduct(){
        return "addProduct";
    }

    @RequestMapping("add")
    public String addProduct(TProduct product) {
        System.out.println(service.addProduct(product));
        return "backIndex";
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
