package com.lzq.activemq.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Queue;
import java.util.UUID;

/**
 * @Author laizhiqiang
 * @Description:
 * @Date 2020/7/5 0005 11:49
 */
@Component
public class Queue_producer {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Queue queue;

    public void produceMsg(){
        jmsMessagingTemplate.convertAndSend(queue,"boot整合activemq:producer."+ UUID.randomUUID().toString().substring(0,6));
    }
}
