package com.ning.modular.controller;

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
@Api(value = "用户", description = "用户", tags = "user", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Resource
    UserService userService;

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
    @PostMapping(value = "list")
    public Result<List> list() {
        return userService.list();
    }
}
