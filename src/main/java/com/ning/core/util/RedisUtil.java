package com.ning.core.util;

import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * Redis缓存工具类
 */
@Component
public class RedisUtil implements ApplicationContextAware {

    private static RedisTemplate<String, Object> redisTemplate;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        redisTemplate = (RedisTemplate<String, Object>) applicationContext.getBean("redisTemplate");
    }

    /**
     * 添加数据
     *
     * @param key
     * @param value
     * @param timeout 超时时间，单位秒 void
     */
    public static void set(String key, Object value, long timeout) {
        redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
    }

    /**
     * 添加数据
     *
     * @param key
     * @param value void
     */
    public static void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 获取数据
     *
     * @param key
     * @param entity
     * @return T
     */
    public static <T> T get(String key, Class<T> entity) {
        return (T) redisTemplate.opsForValue().get(key);
    }

    /**
     * 获取数据
     *
     * @param key
     * @return Object
     */
    public static Object get(String key) {
        return get(key, Object.class);
    }

    /**
     * 删除数据
     *
     * @param key void
     */
    public static void del(String... key) {
        redisTemplate.delete(Arrays.asList(key));
    }

    /**
     * 递增
     *
     * @param key
     * @param delta 增量值
     * @return Long
     */
    public static Long incr(String key, long delta) {
        return redisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * 查询Key
     *
     * @param prefix
     * @param suffix
     * @return
     */
    public static Set<String> getKeys(String prefix, String suffix) {
        if (null == prefix) {
            prefix = "";
        }
        if (null == suffix) {
            suffix = "";
        }
        return redisTemplate.keys(prefix + "*" + suffix);
    }
}
