package com.lzq.activemq.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.Topic;
import java.util.UUID;

/**
 * @Author laizhiqiang
 * @Description:
 * @Date 2020/7/5 0005 17:20
 */
@Component
public class TopicProducerService {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Topic topic;

    @Scheduled(fixedDelay = 3000)
    public void topicSend(){
        jmsMessagingTemplate.convertAndSend(topic,"主题消息："+UUID.randomUUID().toString().substring(0,6));
    }
}
