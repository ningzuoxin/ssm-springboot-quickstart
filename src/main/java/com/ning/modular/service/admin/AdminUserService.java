package com.ning.modular.service.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ning.modular.dao.UserDao;
import com.ning.modular.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminUserService {

    @Resource
    UserDao userDao;

    /**
     * 分页查询用户列表
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    public Page<User> selectPage(Integer pageNum, Integer pageSize) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        Page<User> userPage = userDao.selectPage(new Page<>(pageNum, pageSize), userQueryWrapper);
        return userPage;
    }

}
