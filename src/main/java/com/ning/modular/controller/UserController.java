package com.ning.modular.controller;

import cn.hutool.core.date.DateUtil;
import com.ning.core.annotation.AccessLimit;
import com.ning.core.model.Result;
import com.ning.modular.entity.User;
import com.ning.modular.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
@Api(value = "用户管理", description = "用户管理", tags = "用户管理", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Resource
    UserService userService;

    /**
     * API防刷
     *
     * @return
     */
    @ApiOperation(value = "API防刷")
    @ResponseBody
    @PostMapping("/moreAPI")
    @AccessLimit(seconds = 5, maxCount = 3, isLogin = false)
    public Result<String> moreAPI() {
        Result<String> result = new Result<>();
        result.setData("SUCCESS => " + DateUtil.now());
        return result;
    }

    /**
     * 添加User
     *
     * @param user
     * @return
     */
    @ApiOperation(value = "添加用户")
    @PostMapping(value = "/add")
    public Result<Boolean> add(@RequestBody User user) {
        return userService.add(user);
    }

    /**
     * 查询列表
     *
     * @return
     */
    @ApiOperation(value = "查询列表")
    @PostMapping(value = "/list")
    public Result<List> list() {
        return userService.list();
    }

    /**
     * 查询用户
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "查询用户")
    @PostMapping(value = "/get")
    public Result<User> get(Integer id) {
        return userService.get(id);
    }

}
