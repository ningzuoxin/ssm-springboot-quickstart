package com.ning.core.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 快捷获取HttpServletRequest,HttpServletResponse
 */
public class HttpContextUtil {

    /**
     * 获取请求的IP地址
     *
     * @return
     */
    public static String getIP() {
        HttpServletRequest request = HttpContextUtil.getRequest();
        if (request == null) {
            return "127.0.0.1";
        } else {
            return request.getRemoteHost();
        }
    }

    /**
     * 获取当前请求中的Request对象
     *
     * @return
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return null;
        } else {
            return requestAttributes.getRequest();
        }
    }

    /**
     * 获取当前请求的Response对象
     *
     * @return
     */
    public static HttpServletResponse getResponse() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return null;
        } else {
            return requestAttributes.getResponse();
        }
    }

    /**
     * 获取请求中所有参数
     *
     * @return
     */
    public static Map<String, String> getRequestParameters() {
        HashMap<String, String> values = new HashMap<>();
        HttpServletRequest request = HttpContextUtil.getRequest();
        if (request == null) {
            return values;
        }
        Enumeration enums = request.getParameterNames();
        while (enums.hasMoreElements()) {
            String paramName = (String) enums.nextElement();
            String paramValue = request.getParameter(paramName);
            values.put(paramName, paramValue);
        }
        return values;
    }

}

