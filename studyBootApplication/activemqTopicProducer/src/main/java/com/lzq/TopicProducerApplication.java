package com.lzq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Author laizhiqiang
 * @Description:
 * @Date 2020/7/5 0005 17:11
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableScheduling
public class TopicProducerApplication {
    public static void main(String[] args) {
        SpringApplication.run(TopicProducerApplication.class,args);
    }
}
