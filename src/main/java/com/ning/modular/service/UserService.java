package com.ning.modular.service;

import cn.hutool.core.util.ObjectUtil;
import com.ning.core.model.Result;
import com.ning.core.util.RedisUtil;
import com.ning.modular.dao.UserDao;
import com.ning.modular.entity.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {

    @Resource
    UserDao userDao;

    /**
     * 添加User
     *
     * @param user
     * @return
     */
    public Result<Boolean> add(User user) {
        Result result = new Result();
        int ret = userDao.insert(user);
        if (ret == 1) {
            result.setData(true);
        } else {
            result.setData(false);
        }
        return result;
    }

    /**
     * 查询列表
     *
     * @return
     */
    public Result list() {
        Result result = new Result();
        List<User> userList = RedisUtil.get("USER_LIST", List.class);
        if (ObjectUtil.isEmpty(userList)) {
            userList = userDao.selectAll();
            RedisUtil.set("USER_LIST", userList, 10000);
        }
        result.setData(userList);
        return result;
    }

    /**
     * 查询用户
     *
     * @param id
     * @return
     */
    // 将方法运行的结果进行缓存，以后再要相同的结果，直接从缓存中获取，不用调用方法。
    // @Cacheable(cacheNames = "USER", key = "#id", condition = "#id > 0", unless = "#result == null")
    @Cacheable(cacheNames = "USER", keyGenerator = "myKeyGenerator", condition = "#id > 0", unless = "#result == null")
    public Result get(Integer id) {
        Result result = new Result();
        result.setData(userDao.selectById(id));
        return result;
    }

}
