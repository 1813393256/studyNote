package com.lzq.dbPersistence;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

import javax.jms.*;

/**
 * Created in      9:17 2020/7/7
 *
 * @Description: [ActiveMQ 持久化数据到数据库，queue测试]
 * @Author: LaiZQ
 * @version: 1.0
 */
public class DBQueue {
    private static final String ACTIVEMQ_URL="nio://47.101.133.169:61616";
    private static final String QUEUE_NAME="db_persistence_queue";

    @Test
    public void producer() throws Exception {
        ActiveMQConnectionFactory factory=new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        Connection connection = factory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(QUEUE_NAME);
        MessageProducer producer = session.createProducer(queue);
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);
        for (int i=0;i<3;i++){
            TextMessage textMessage = session.createTextMessage("持久化到数据库:"+i);
            producer.send(textMessage);
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
            message=consumer.receive(1000l);
        }
        consumer.close();
        session.close();
        connection.close();
    }

}
