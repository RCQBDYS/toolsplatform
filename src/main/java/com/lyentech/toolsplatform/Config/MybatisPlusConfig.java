package com.lyentech.toolsplatform.Config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * @author 672025
 * @date  9:27 2020/9/17
 * @description Mybatis-plus的配置
 */

@Configuration
//数据库中的事务管理器的开启
@EnableTransactionManagement
//Mapper文件的指定扫描路径的配置，而不是xml文件

@MapperScan("com.lyentech.toolsplatform.*.mapper")
public class MybatisPlusConfig {

    //分页插件的配置

    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }
}
