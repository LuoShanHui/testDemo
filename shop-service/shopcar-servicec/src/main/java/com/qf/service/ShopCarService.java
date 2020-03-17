package com.qf.service;


import com.qf.dto.ResultBean;

public interface ShopCarService {
    ResultBean addProduct(String uuid, Long productId, int count);

    ResultBean clean(String uuid);

    ResultBean update(String uuid, Long productId, int count);

    ResultBean showCart(String uuid);
}
