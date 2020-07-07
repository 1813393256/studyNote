package com.lzq.amqScheduled;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQMessageProducer;
import org.apache.activemq.AsyncCallback;
import org.apache.activemq.ScheduledMessage;
import org.junit.Test;

import javax.jms.*;
import java.util.UUID;

/**
 * Created in      15:58 2020/7/7
 *
 * @Description: [延时投递，定时投递]
 * Copyright:      Copyright (c) 2016
 * @Author: LaiZQ
 * @version: 1.0
 */
public class ScheduledQueue {

    private static final String ACTIVEMQ_URL="tcp://47.101.133.169:61616";
    private static final String QUEUE_NAME="scheduled_queue";

    @Test
    public void producer() throws Exception {
        ActiveMQConnectionFactory factory=new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        factory.setUseAsyncSend(true);
        Connection connection = factory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(QUEUE_NAME);
        MessageProducer producer = session.createProducer(queue);
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);
        Long delay=2*1000l;//延迟2秒后发送
        Long perior=4*1000l;//消息间隔4秒
        int repeat=5;//重复发送4次
        for (int i=0;i<3;i++){
            Message message = session.createTextMessage("async持久化到数据库:"+i);
            message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY,delay);
            message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_PERIOD, perior);
            message.setIntProperty(ScheduledMessage.AMQ_SCHEDULED_REPEAT, repeat);
            producer.send(message);
        }
        System.out.println("queue发送完成");
        producer.close();
        session.close();
        connection.close();
    }

    @Test
    public void consumer() throws Exception{
        ActiveMQConnectionFactory factory=new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        Connection connection = factory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(QUEUE_NAME);
        MessageConsumer consumer = session.createConsumer(queue);
        Message message = consumer.receive();
        while (message!=null){
            TextMessage textMessage= (TextMessage) message;
            System.out.println("消费者接收到的数据："+textMessage.getText());
            message=consumer.receive();
        }
        consumer.close();
        session.close();
        connection.close();
    }

}
