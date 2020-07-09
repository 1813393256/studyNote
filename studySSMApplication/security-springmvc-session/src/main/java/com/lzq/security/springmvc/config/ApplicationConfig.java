package com.lzq.security.springmvc.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

/**
 * Created in      14:15 2020/7/9
 *
 * @Description: [spring容器配置，对应于web.xml中ContextLoaderListener的配置]
 * @Author: LaiZQ
 * @version: 1.0
 */

@Configuration//相当于applicationContext.xml配置文件
@ComponentScan(basePackages = "com.lzq.security.springmvc",
        excludeFilters = {@ComponentScan.Filter(type= FilterType.ANNOTATION,value = Controller.class)})
public class ApplicationConfig {
    //在此配置除了Controller的其他bean，比如:数据库连接池、事物管理器、业务bean等。

}
