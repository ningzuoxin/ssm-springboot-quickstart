package com.ning.modular.controller.admin;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.ning.modular.entity.User;
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
     * 后台登录页面
     *
     * @param username
     * @param password
     * @param request
     * @return
     */
    @RequestMapping(value = {"", "/", "login"})
    public String login(@RequestParam(required = false) String username,
                        @RequestParam(required = false) String password,
                        HttpServletRequest request) {

        if (StrUtil.isEmpty(username) || StrUtil.isEmpty(password)) {
            return "login.html";
        }

        User user = adminUserService.login(username.trim(), password.trim());
        if (ObjectUtil.isEmpty(user)) {
            return "login.html";
        }

        request.getSession().setAttribute("USER", user);
        return "redirect:/admin/index";
    }

    /**
     * 退出
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/loginOut")
    public String loginOut(HttpServletRequest request) {
        request.getSession().removeAttribute("USER");
        return "login.html";
    }

    /**
     * 后台首页
     *
     * @return
     */
    @RequestMapping(value = {"index"})
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
