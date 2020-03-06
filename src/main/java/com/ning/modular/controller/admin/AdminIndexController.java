package com.ning.modular.controller.admin;

import com.ning.modular.service.admin.AdminUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/admin")
public class AdminIndexController {

    @Resource
    AdminUserService adminUserService;

    /**
     * 后台首页
     *
     * @return
     */
    @RequestMapping(value = {"", "/", "index"})
    public String index(HttpServletRequest request) {
        request.setAttribute("title", "Beelt Admin ~");
        return "index.html";
    }

    /**
     * 用户列表
     *
     * @return
     */
    @RequestMapping(value = "/users")
    public String users(@RequestParam(required = false, defaultValue = "0") Integer pageNum,
                        @RequestParam(required = false, defaultValue = "8") Integer pageSize,
                        HttpServletRequest request) {
        request.setAttribute("users", adminUserService.selectPage(pageNum, pageSize));
        return "user_list.html";
    }

}
