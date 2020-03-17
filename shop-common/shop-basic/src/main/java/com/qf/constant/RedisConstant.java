package com.qf.constant;

/**
 * @Author Administrator
 * @PACKAGE myteam-shop
 */
public class RedisConstant {
    /**
     * 设置email服务的数据存入redis的key
     */
    public static final String REGIST_EMAIL_URL_KEY_PRE = "regist:email:url:";

    /**
     * 设置index初始化数据存入redis的key:index-set-product_type
     */
    public static final String INDEX_SET_PRODUCT_TYPE = "index-set-product_type";

    /**
     * 用户登录key的前部分
     */
    public static final String USER_LOGIN_PRE = "user:login:";

}
