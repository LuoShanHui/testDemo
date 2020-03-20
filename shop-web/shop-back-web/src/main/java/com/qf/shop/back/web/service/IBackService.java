package com.qf.shop.back.web.service;

import com.qf.dto.ResultBean;
import com.qf.entity.TProduct;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author Administrator
 * @PACKAGE myteam-shop
 */
@FeignClient("BACK-SERVICE")
public interface IBackService {

    @RequestMapping("back/selectAllProduct")
    ResultBean selectAllProduct();

    @RequestMapping(value = "back/addProduct", method = RequestMethod.POST)
    ResultBean addProduct(@RequestBody TProduct product);

    @RequestMapping("back/findProductById")
    ResultBean findProductById(@RequestParam Long id);

    @RequestMapping(value = "back/updateProduct",method = RequestMethod.POST)
    ResultBean updateProduct(@RequestBody TProduct product);

    @RequestMapping("back/deleteProduct")
    ResultBean deleteProduct(@RequestParam Long id);
}
