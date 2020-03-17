package com.qf.util;

/**
 * @Author Administrator
 * @PACKAGE myteam-shop
 */
public class RedisUtil {

    /**
     * 字符串拼接
     * 例如:
     * pre为regist:email:url:
     * key为128731-sdfl-123
     * 返回regist:email:url:128731-sdfl-123
     *
     * @param pre
     * @param key
     * @return 拼接字符串
     */
    public static String getRedisKey(String pre, String key) {
        return new StringBuilder().append(pre).append(key).toString();
    }
}
