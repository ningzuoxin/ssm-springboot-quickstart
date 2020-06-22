package com.ning.modular.controller;

import cn.hutool.core.date.DateUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class BaseController {

    /**
     * 根路径
     *
     * @return
     */
    @RequestMapping(value = {"", "/"})
    public String index() {
        return DateUtil.now();
    }

    @PostMapping(value = "/login")
    public void login(HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        System.out.println("parameterMap => " + parameterMap);
    }

}
