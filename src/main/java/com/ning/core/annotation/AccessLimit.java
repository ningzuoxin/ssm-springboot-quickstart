package com.ning.core.annotation;

import java.lang.annotation.*;

/**
 * 自定义防刷注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AccessLimit {

    // 时间段
    int seconds() default 10;

    // 时间段内最大访问次数
    int maxCount() default 5;

    boolean isLogin() default true;
}
