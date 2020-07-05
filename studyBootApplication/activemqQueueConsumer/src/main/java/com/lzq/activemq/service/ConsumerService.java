package com.lzq.activemq.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.TextMessage;

/**
 * @Author laizhiqiang
 * @Description: 微服务，消费者服务类，activemq
 * @Date 2020/7/5 0005 16:41
 */
@Component
public class ConsumerService {
    @JmsListener(destination = "${myqueue}")
    public void method_receive(TextMessage textMessage) throws JMSException {
        System.out.println("消费者收到消息**"+textMessage.getText());
    }
}
