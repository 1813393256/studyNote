package com.lzq.activemq.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.stereotype.Component;

import javax.jms.Queue;

/**
 * @Author laizhiqiang
 * @Description:
 * @Date 2020/7/5 0005 11:30
 */
@Component
@EnableJms
public class ConfigBean {
    @Value("${myqueue}")
    private String myQueue;
    @Bean //<bean id="" class="">
    public Queue queue(){
        return new ActiveMQQueue(myQueue);
    }
}
