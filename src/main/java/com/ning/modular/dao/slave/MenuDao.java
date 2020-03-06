package com.ning.modular.dao.slave;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.ning.modular.entity.slave.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Mr.code
 * @since 2020-03-06
 */
@DS("slave") // 使用从数据库
public interface MenuDao extends BaseMapper<Menu> {

}
