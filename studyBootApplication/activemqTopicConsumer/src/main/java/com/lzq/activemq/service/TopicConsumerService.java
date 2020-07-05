package com.lzq.activemq.service;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.TextMessage;

/**
 * @Author laizhiqiang
 * @Description: 主题接收者
 * @Date 2020/7/5 0005 17:22
 */
@Component
public class TopicConsumerService {
    @JmsListener(destination = "${mytopic}")
    private void topicReceive(TextMessage textMessage) throws JMSException {
        System.out.println(textMessage.getText());
    }
}
