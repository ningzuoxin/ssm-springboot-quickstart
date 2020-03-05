package com.ning.modular.controller;

import cn.hutool.core.date.DateUtil;
import com.ning.core.annotation.AccessLimit;
import com.ning.core.model.Result;
import com.ning.core.util.SignUtil;
import com.ning.modular.entity.User;
import com.ning.modular.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/user")
@Api(value = "用户管理", description = "用户管理", tags = "用户管理", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    /**
     * 利用秘钥生成签名（只有对方知，服务器知），校验请求源合法性，不同源可以设置不同的秘钥
     */
    private static final String API_SECRET_KEY = "996";

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


    /**
     * http://localhost:9999/test?name=minbo&age=100&sign=495FC6F52324AB1460C95A27803E3A4A
     *
     * @param name
     * @param age
     * @param sign 大写
     * @return
     */
    @ApiOperation(value = "秘钥防刷")
    @GetMapping("/test")
    public Result test(String name, Integer age, String sign, HttpServletRequest request) {
        Result<String> result = new Result<>();
        // 1. 还可以在参数中增加一个动态随机字符参数，比如sId，每次请求时，对方都需要动态生成一个十位随机字符，防止sign值一直固化不变
        // 2. 同时，服务器可以校验请求是否重复，比如可以通过redis存储已请求过的rId（可设置过期时间，以免一直存储历史的rId值），防止别人利用固定请求链接刷请求
        // 3. 可以使用公网ip，限制同一个ip访问次数（也可以在nginx层做限制，这个自行网上了解了）

        // 获取公网ip
//		String sIp = NetworkUtil.getIpAddress(request);
//		System.out.println("sIp = " + sIp);

        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        params.put("age", age);
        String serverSign = SignUtil.signByMD5(params, API_SECRET_KEY);
        if (serverSign.equals(sign)) {
            result.setData("签名通过~");
        } else {
            result.setData("非法请求~");
        }
        return result;
    }

}
