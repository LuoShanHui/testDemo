package com.qf.mapper;



import com.qf.entity.TProduct;

import java.util.List;

public interface TProductMapper  {

    int deleteByPrimaryKey(Long cid);

    int insert(TProduct record);

    int insertSelective(TProduct record);

    TProduct selectByPrimaryKey(Long cid);

    int updateByPrimaryKeySelective(TProduct record);

    int updateByPrimaryKey(TProduct record);
}
