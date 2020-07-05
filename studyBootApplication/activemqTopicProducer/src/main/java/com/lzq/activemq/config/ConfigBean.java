package com.lzq.activemq.config;

import org.apache.activemq.command.ActiveMQTempTopic;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.stereotype.Component;

import javax.jms.Topic;

/**
 * @Author laizhiqiang
 * @Description: 配置类，装配bean
 * @Date 2020/7/5 0005 17:14
 */
@Component
@EnableJms
public class ConfigBean {
    @Value("${mytopic}")
    private String topicName;
    @Bean
    public Topic topic(){
        return new ActiveMQTopic(topicName);
    }
}
