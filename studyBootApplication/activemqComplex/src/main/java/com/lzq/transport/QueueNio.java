package com.lzq.transport;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

import javax.jms.*;
import java.io.IOException;

/**
 * @Author laizhiqiang
 * @Description: activemq演示,传输协议测试
 * @Date 2020/7/5 0005 19:48
 */
public class QueueNio {
    private static final String ACTIVEMQ_URL="nio://47.101.133.169:61618";
    private static final String QUEUE_NAME="nio_name";

    @Test
    public void nioQueueProducer() throws JMSException {
        ActiveMQConnectionFactory factory=new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        Connection connection = factory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(QUEUE_NAME);
        MessageProducer producer = session.createProducer(queue);
        for (int i=0;i<3;i++){
            TextMessage textMessage = session.createTextMessage("nioMsg:"+i);
            producer.send(textMessage);
        }
        System.out.println("nio传输完成");
        producer.close();
        session.close();
        connection.close();
    }
    @Test
    public void nioQueueConsumer() throws JMSException, IOException {
        ActiveMQConnectionFactory factory=new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        Connection connection = factory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(QUEUE_NAME);
        MessageConsumer consumer = session.createConsumer(queue);
        Message msg = consumer.receive();
        while (msg!=null){
            TextMessage textMessage= (TextMessage) msg;
            System.out.println(textMessage.getText());
            msg=consumer.receive(1000);
        }
        consumer.close();
        session.close();
        connection.close();
    }

}
