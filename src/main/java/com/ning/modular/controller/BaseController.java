package com.ning.modular.controller;

import cn.hutool.core.date.DateUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
