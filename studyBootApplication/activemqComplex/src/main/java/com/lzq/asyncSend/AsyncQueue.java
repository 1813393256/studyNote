package com.lzq.asyncSend;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQMessageProducer;
import org.apache.activemq.AsyncCallback;
import org.junit.Test;

import javax.jms.*;
import java.util.UUID;

/**
 * Created in      15:04 2020/7/7
 *
 * @Description: [ActiveMQ异步发送,Queue,解决  异步发送如何确保发送成功问题  ]
 * Copyright:      Copyright (c) 2016
 * @Author: LaiZQ
 * @version: 1.0
 */
public class AsyncQueue {
    private static final String ACTIVEMQ_URL="tcp://47.101.133.169:61616";
    private static final String QUEUE_NAME="async_queue";

    @Test
    public void producer() throws Exception {
        ActiveMQConnectionFactory factory=new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        factory.setUseAsyncSend(true);
        Connection connection = factory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(QUEUE_NAME);
        ActiveMQMessageProducer activeMQMessageProducer = (ActiveMQMessageProducer) session.createProducer(queue);
        activeMQMessageProducer.setDeliveryMode(DeliveryMode.PERSISTENT);
        for (int i=0;i<3;i++){
            Message message = session.createTextMessage("async持久化到数据库:"+i);
            message.setJMSMessageID(UUID.randomUUID().toString()+"--异步发送成功确认？--");
            String jmsMessageID = message.getJMSMessageID();
            activeMQMessageProducer.send(message, new AsyncCallback() {
                @Override
                public void onSuccess() {
                    System.out.println(jmsMessageID+": has been ok send");
                }

                @Override
                public void onException(JMSException e) {
                    System.out.println(jmsMessageID+": fail to send of activemq");
                }
            });
        }
        System.out.println("queue发送完成");
        activeMQMessageProducer.close();
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
            message=consumer.receive(1000l);
        }
        consumer.close();
        session.close();
        connection.close();
    }
}
