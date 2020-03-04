package com.ning.modular.service;

import com.ning.core.model.Result;
import com.ning.modular.dao.UserDao;
import com.ning.modular.entity.User;
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
        List<User> userList = userDao.selectAll();
        result.setData(userList);
        return result;
    }

}
