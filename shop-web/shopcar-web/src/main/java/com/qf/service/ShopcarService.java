package com.qf.service;

import com.qf.constant.CookieConstant;
import com.qf.dto.ResultBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

@FeignClient(value = "SHOPCAR-SERVICE")
public interface ShopcarService {
    @RequestMapping("show")
    ResultBean showCart(@CookieValue(name= CookieConstant.USER_CART,required = false)String uuid);

    @RequestMapping("update/{productId}/{count}")
     ResultBean updateCart(
            @PathVariable Long productId,
            @PathVariable int count,
            @CookieValue(name=CookieConstant.USER_CART,required = false)String uuid
    );

    @RequestMapping("clean")
     ResultBean cleanCart(@CookieValue(name=CookieConstant.USER_CART,required = false)String uuid,
                                HttpServletResponse response);

    @RequestMapping("add/{productId}/{count}")
     ResultBean addProduct(@CookieValue(name = CookieConstant.USER_CART, required = false) String uuid,
                                 @PathVariable Long productId,
                                 @PathVariable int count,
                                 HttpServletResponse response);
}
