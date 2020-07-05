package com.lzq.activemq.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
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
        jmsMessagingTemplate.convertAndSend(queue,"******"+ UUID.randomUUID().toString().substring(0,6));
    }
    //间隔时间3秒钟定投，要求主启动类加@EnableScheduling
    @Scheduled(fixedDelay = 3000)
    public void produceMsgScheduled(){
        jmsMessagingTemplate.convertAndSend(queue,"****Scheduled"+ UUID.randomUUID().toString().substring(0,6));
        System.out.println("*****produceMsgScheduled send ok");
    }
}
