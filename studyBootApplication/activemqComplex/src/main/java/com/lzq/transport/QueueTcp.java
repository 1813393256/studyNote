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
public class QueueTcp {
    private static final String ACTIVEMQ_URL="tcp://47.101.133.169:61616";
    private static final String QUEUE_NAME="tcp_name";

    @Test
    public void tcpQueueProducer() throws JMSException {
        ActiveMQConnectionFactory factory=new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        Connection connection = factory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(QUEUE_NAME);
        MessageProducer producer = session.createProducer(queue);
        for (int i=0;i<3;i++){
            TextMessage textMessage = session.createTextMessage("tcpMsg:"+i);
            producer.send(textMessage);
        }
        System.out.println("tcp传输完成");
        producer.close();
        session.close();
        connection.close();
    }
    @Test
    public void tcpQueueConsumer() throws JMSException, IOException {
        ActiveMQConnectionFactory factory=new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        Connection connection = factory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(QUEUE_NAME);
        MessageConsumer consumer = session.createConsumer(queue);
        consumer.setMessageListener(message -> {
            if (null!=message&&message instanceof TextMessage){
                TextMessage textMessage= (TextMessage) message;
                try {
                    System.out.println(textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        consumer.close();
        session.close();
        connection.close();
    }

}
