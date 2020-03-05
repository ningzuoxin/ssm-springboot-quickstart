package com.ning.core.aspect;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;
import com.ning.core.annotation.AccessLimit;
import com.ning.core.exception.LimitException;
import com.ning.core.model.Result;
import com.ning.core.util.HttpContextUtil;
import com.ning.core.util.RedisUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

/**
 * 基于AOP实现接口防刷
 */
@SuppressWarnings("ALL")
@Component
@Order
@Aspect
public class LimitAspect {

    /**
     * 切入点
     *
     * @param accessLimit
     */
    @Pointcut("@annotation(accessLimit)")
    public void limit(AccessLimit accessLimit) {
    }

    @Around("limit(accessLimit)")
    public Object aroundLog(ProceedingJoinPoint joinpoint, AccessLimit accessLimit) throws Throwable {

        HttpServletRequest request = HttpContextUtil.getRequest();
        HttpServletResponse response = HttpContextUtil.getResponse();
        String key = request.getRequestURI();
        System.out.println("LimitAspect aroundLog key => " + key);

        int seconds = accessLimit.seconds();
        int maxCount = accessLimit.maxCount();
        boolean login = accessLimit.isLogin();

        // 如果需要登录
        if (login) {
            // 获取登录的session进行判断
            key += "" + "1";  //这里假设用户是1,项目中是动态获取的userId
        }

        // 从redis中获取用户访问的次数
        Integer count = RedisUtil.get(key, Integer.class);
        if (count == null) {
            // 第一次访问
            RedisUtil.set(key, 1, seconds);
        } else if (count < maxCount) {
            // 加1
            RedisUtil.incr(key, 1);
        } else {
            throw new LimitException("请勿频繁请求 => " + DateUtil.now());
            // 超出访问次数
            // render(response); // 这里的CodeMsg是一个返回参数
        }
        return joinpoint.proceed();
    }

    private void render(HttpServletResponse response) throws Exception {
        response.setContentType("application/json;charset=UTF-8");
        OutputStream out = response.getOutputStream();
        Result<String> result = new Result<>();
        result.setData("请勿频繁访问该接口~");
        String str = JSONUtil.toJsonStr(result);
        out.write(str.getBytes("UTF-8"));
        out.flush();
        out.close();
    }

}
