package com.ning.modular.controller;

import com.ning.core.model.Result;
import com.ning.modular.entity.User;
import com.ning.modular.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Resource
    UserService userService;

    /**
     * 添加User
     *
     * @param user
     * @return
     */
    @PostMapping(value = "/add")
    public Result<Boolean> add(@RequestBody User user) {
        return userService.add(user);
    }

    /**
     * 查询列表
     *
     * @return
     */
    @PostMapping(value = "list")
    public Result<List> list() {
        return userService.list();
    }
}
