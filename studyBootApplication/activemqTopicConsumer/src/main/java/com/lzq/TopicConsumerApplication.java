package com.lzq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @Author laizhiqiang
 * @Description:
 * @Date 2020/7/5 0005 17:13
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class TopicConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(TopicConsumerApplication.class,args);
    }
}
