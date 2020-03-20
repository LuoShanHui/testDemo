package com.qf.shop.back.service.service.impl;

import com.qf.dto.ResultBean;
import com.qf.entity.TProduct;
import com.qf.mapper.TProductMapper;
import com.qf.shop.back.service.service.IBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Administrator
 * @PACKAGE myteam-shop
 */
@Service
public class BackServiceImpl implements IBackService {

    @Autowired
    private TProductMapper mapper;


    @Override
    public ResultBean addProduct(TProduct product) {
        int result = mapper.insertSelective(product);
        if (result > 0) {
            return ResultBean.success("添加成功");
        }
        return ResultBean.error("添加失败");
    }

    @Override
    public List<TProduct> selectAllProduct() {
        return mapper.selectAll();
    }

    @Override
    public ResultBean updateProduct(TProduct product) {
        int result = mapper.updateByPrimaryKeySelective(product);
        if (result > 0) {
            return ResultBean.success("修改成功");
        }
        return ResultBean.error("修改失败");
    }

    @Override
    public ResultBean deleteProduct(Long id) {
        TProduct product = new TProduct();
        product.setPid(id);
        product.setFlag((byte) 0);
        int result = mapper.updateByPrimaryKeySelective(product);
        if (result > 0) {
            return ResultBean.success("删除成功");
        }
        return ResultBean.error("删除失败");
    }

    @Override
    public ResultBean findProductById(Long id) {
        TProduct product = mapper.selectByPrimaryKey(id);
        if (product != null) {
            return ResultBean.success(product, "查询成功");
        }
        return ResultBean.error();
    }


}
