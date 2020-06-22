package com.ning.config;

import com.ning.core.interceptor.JwtAuthenticationInterceptor;
import com.ning.core.interceptor.LimitInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Resource
    LimitInterceptor limitInterceptor;
    @Resource
    JwtAuthenticationInterceptor jwtAuthenticationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 不拦截的url
        List<String> excludePathPatterns = new ArrayList<>();
        excludePathPatterns.add("/swagger-resources");
        excludePathPatterns.add("/swagger-resources/**");
        excludePathPatterns.add("/doc.html");
        excludePathPatterns.add("/error");
//        registry.addInterceptor(limitInterceptor).excludePathPatterns(excludePathPatterns).addPathPatterns("/**");
//        registry.addInterceptor(jwtAuthenticationInterceptor).excludePathPatterns(excludePathPatterns).addPathPatterns("/**");
    }

}
