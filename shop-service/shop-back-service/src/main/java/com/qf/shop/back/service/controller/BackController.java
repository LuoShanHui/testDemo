package com.qf.shop.back.service.controller;

import com.qf.constant.RedisConstant;
import com.qf.dto.ResultBean;
import com.qf.entity.TProduct;
import com.qf.shop.back.service.service.IBackService;
import com.sun.xml.internal.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author Administrator
 * @PACKAGE myteam-shop
 */
@RestController
@RequestMapping("back")
public class BackController {

    @Autowired
    private IBackService service;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 查询所有商品
     *
     * @return
     */
    @RequestMapping("selectAllProduct")
    public ResultBean selectAllProduct() {
        List<TProduct> productList = service.selectAllProduct();
        return ResultBean.success(productList, "查询所有商品成功");
    }

    /**
     * 添加商品
     *
     * @param product
     * @return
     */
    @RequestMapping("addProduct")
    public ResultBean addProduct(@RequestBody TProduct product) {
        ResultBean resultBean = service.addProduct(product);
        // TODO 修改储存在redis上的数据
        try {
            updateProductForRedis(resultBean);
        } catch (Exception e) {
            return resultBean;
        }
        return resultBean;
    }

    /**
     * 修改商品
     *
     * @param product
     * @return
     */
    @RequestMapping("updateProduct")
    public ResultBean updateProduct(@RequestBody TProduct product) {
        ResultBean resultBean = service.updateProduct(product);
        //TODO 修改储存在redis上的数据
        try {
            updateProductForRedis(resultBean);
        } catch (Exception e) {
            return resultBean;
        }
        return resultBean;
    }

    /**
     * 删除商品(修改有效性)
     *
     * @param id
     * @return
     */
    @RequestMapping("deleteProduct")
    public ResultBean deleteProduct(Long id) {
        ResultBean resultBean = service.deleteProduct(id);
        //TODO 修改储存在redis上的数据
        try {
            updateProductForRedis(resultBean);
        } catch (Exception e) {
            return resultBean;
        }
        return resultBean;
    }

    /**
     * 修改储存在redis上的Product集合数据
     *
     * @param resultBean
     */
    private void updateProductForRedis(ResultBean resultBean) {
        if (resultBean.getErrno() == 0) {
            //将查到的商品集合放到redis上
            List<TProduct> productList = service.selectAllProduct();

            //清空redis中改key值对应的数据
            while (redisTemplate.opsForList().size(RedisConstant.INDEX_SET_PRODUCT) > 0) {
                redisTemplate.opsForList().leftPop(RedisConstant.INDEX_SET_PRODUCT);
            }
            //将查到的商品列别集合放到redis上
            redisTemplate.opsForList().rightPush(RedisConstant.INDEX_SET_PRODUCT, productList);
        }
    }
}
