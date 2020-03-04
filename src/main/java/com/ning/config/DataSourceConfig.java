package com.ning.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * 数据源相关配置
 */
@Configuration
@MapperScan(value = "com.ning.modular.dao") // 扫描Mapper接口
public class DataSourceConfig {
}
