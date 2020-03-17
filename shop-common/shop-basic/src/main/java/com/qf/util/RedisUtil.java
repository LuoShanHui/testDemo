package com.qf.util;

/**
 * @Author Administrator
 * @PACKAGE myteam-shop
 */
public class RedisUtil {

    public static String getRedisKey(String pre,String key){
        return new StringBuilder().append(pre).append(key).toString();
    }
}
