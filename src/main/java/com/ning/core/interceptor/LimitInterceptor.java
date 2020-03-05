package com.ning.core.interceptor;

import cn.hutool.json.JSONUtil;
import com.ning.core.annotation.AccessLimit;
import com.ning.core.model.Result;
import com.ning.core.util.RedisUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

/**
 * 接口防刷拦截器
 */
@Component
public class LimitInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String key = request.getRequestURI();
        System.out.println("LimitInterceptor preHandle key => " + key);

        // 判断请求是否属于方法的请求
        if (handler instanceof HandlerMethod) {
            HandlerMethod hm = (HandlerMethod) handler;
            // 获取方法中的注解,看是否有该注解
            AccessLimit accessLimit = hm.getMethodAnnotation(AccessLimit.class);
            if (accessLimit == null) {
                return true;
            }

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
                // 超出访问次数
                render(response); // 这里的CodeMsg是一个返回参数
                return false;
            }
        }
        return true;
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
