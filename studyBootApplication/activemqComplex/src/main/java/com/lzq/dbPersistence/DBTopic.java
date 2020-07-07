package com.lzq.dbPersistence;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

import javax.jms.*;

/**
 * Created in      9:42 2020/7/7
 *
 * @Description: [ActiveMQ持久化到数据库，topic测试]
 * Department:     研发部
 * @Author: LaiZQ
 * @version: 1.0
 */
public class DBTopic {
    private static final String ACTIVEMQ_URL="nio://47.101.133.169:61616";
    private static final String TOPIC_NAME="db_persistence_topic";

    @Test
    public void producer() throws Exception{
        ActiveMQConnectionFactory factory=new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        Connection connection = factory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic(TOPIC_NAME);
        MessageProducer producer = session.createProducer(topic);
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);
        for (int i=0;i<3;i++){
            TextMessage textMessage = session.createTextMessage("topic持久化到数据库:"+i);
            producer.send(textMessage);
        }
        System.out.println("topic发送完成");
        producer.close();
        session.close();
        connection.close();
    }

    @Test
    public void consumer()throws Exception{
        ActiveMQConnectionFactory factory=new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        Connection connection = factory.createConnection();
        connection.setClientID("topic_consumer01");
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic(TOPIC_NAME);
        TopicSubscriber subscriber = session.createDurableSubscriber(topic, "小小");
        connection.start();
        Message message = subscriber.receive();
        while (message!=null){
            TextMessage textMessage= (TextMessage) message;
            System.out.println("topic接收到："+textMessage.getText());
            message = subscriber.receive(1000l);
        }
        subscriber.close();
        session.close();
        connection.close();
    }
}
