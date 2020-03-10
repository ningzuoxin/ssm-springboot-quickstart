package com.ning.core.interceptor;

import com.ning.core.annotation.PassToken;
import com.ning.core.util.JwtUtils;
import com.ning.modular.dao.UserDao;
import com.ning.modular.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * jwt令牌拦截器
 */
@Component
public class JwtAuthenticationInterceptor extends HandlerInterceptorAdapter {

    @Resource
    UserDao userDao;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse response, Object handler) throws Exception {

        // 从请求头中取出 token 这里需要和前端约定好把jwt放到请求头一个叫token的地方
        String token = httpServletRequest.getHeader("token");
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        // 检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        } else { // 默认全部检查
            System.out.println("被jwt拦截需要验证");
            // 执行认证
            if (token == null) {
                // 这里其实是登录失效,没token了，这个错误也是我自定义的，读者需要自己修改
                throw new RuntimeException("需要登录，没有发现携带令牌~");
            }

            // 获取 token 中的 user Name
            String userId = JwtUtils.getAudience(token);

            // 找找看是否有这个user 因为我们需要检查用户是否存在，读者可以自行修改逻辑
            User user = userDao.selectById(userId);

            if (user == null) {
                // 这个错误也是我自定义的
                throw new RuntimeException("没有发现有效的用户信息~");
            }

            // 验证 token
            JwtUtils.verifyToken(token, userId);

            // 获取载荷内容
            String userName = JwtUtils.getClaimByName(token, "userName").asString();
            String realName = JwtUtils.getClaimByName(token, "realName").asString();

            // 放入attribute以便后面调用
            httpServletRequest.setAttribute("userName", userName);
            httpServletRequest.setAttribute("realName", realName);

            return true;
        }
        return true;
    }
}
