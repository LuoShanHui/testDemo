package com.qf.shop.regist.service.service.impl;

import com.qf.dto.ResultBean;
import com.qf.entity.TUser;
import com.qf.mapper.TUserMapper;
import com.qf.shop.regist.service.service.IRegistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author Administrator
 * @PACKAGE myteam-shop
 */
@Service
public class RegistServiceImpl implements IRegistService {

    @Autowired
    private TUserMapper mapper;

    /**
     * 存储返回的id
     */
    private long id = -1L;

    /**
     * 添加要注册的账号信息到数据库内
     *
     * @param addr
     * @param password
     * @return
     */
    @Override
    public ResultBean regist(String addr, String password) {
        TUser user = new TUser();
        user.setUname(addr.substring(0, addr.lastIndexOf("@")));
        user.setPassword(password);
        user.setEmail(addr);

        mapper.insertSelective(user);

        if (user.getId()!=0) {
            id=user.getId();
            return ResultBean.success("注册信息成功!!!还需激活账号");
        } else {
            return ResultBean.error("注册失败!!!");
        }
    }

    /**
     * 激活账号
     *
     * @param uname
     * @return
     */
    @Override
    public ResultBean updateFlag(String uname) {
        int result = 0;
        if (id > 0) {
            TUser user = new TUser();
            user.setUname(uname);
            user.setId(id);
            user.setFlag(true);
            result = mapper.updateByPrimaryKeySelective(user);
            id = -1;
        }
        if (result > 0) {
            return ResultBean.success("账号激活成功!!");
        }
        return ResultBean.error("账号激活失败!!");

    }
}
