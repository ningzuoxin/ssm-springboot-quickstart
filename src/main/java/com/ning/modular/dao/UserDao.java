package com.ning.modular.dao;

import com.ning.modular.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Mr.code
 * @since 2020-03-04
 */
public interface UserDao extends BaseMapper<User> {

    List<User> selectAll();

}
