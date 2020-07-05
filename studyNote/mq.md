# MQ(消息中间件)

## MQ的产品种类

1. Kafka
2. RabbitMQ
3. RocketMQ
4. ActiveMQ
5. 其他

## MQ比较

![](https://img-blog.csdn.net/20170816171523564?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvb01hdmVyaWNrMQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

## MQ之ActiveMQ

1. api发送和接收
2. MQ的高可用性
3. MQ的集群和容错配置
4. MQ的持久化
5. 延时发送/定时投递
6. 签收机制
7. spring整合
8. 其他

## pom.xml

```
<dependency>
    <groupId>org.apache.activemq</groupId>
    <artifactId>activemq-all</artifactId>
    <version>5.14.5</version>
</dependency>
<dependency>
    <groupId>org.apache.xbean</groupId>
    <artifactId>xbean-spring</artifactId>
    <version>3.16</version>
</dependency>
```

## MQ能干嘛

1. 解耦：当新的板块接进来时，可以做到代码改动最小
2. 削峰：设置流量缓存池，可以让后端系统按照自身吞吐能力进行消费，不被冲垮
3. 异步：强弱依赖梳理能够将非关键调用链路的操作异步化并提升整体系统的吞吐能力
4. 消息之间要异步，系统之间要解耦

## 面向消息的中间件MOM

1. 利用高效可靠的消息传递机制进行与平台无关的数据交流，并基于数据通信来进行分布式系统的集成。通过提供消息传递与消息排队模型在分布式环境下提供应用解耦、弹性伸缩、沉余存储、流量削峰、异步通信、数据同步等功能。
2. 发送者将消息发送给服务器，消息服务器将消息存放在若干个队列/主题topic中，在合适的时候，消息服务器会将消息转发给接受者。在这个过程中，发送和接收是异步的，即发送无需等待，而且发送者和接收者的生命周期也没有必然关系；尤其在发布pub/订阅sub模式下，也可以完成一对多的通信，即让一个消息有多个接收者。

## Linux安装ActiveMQ

activemq的默认进程端口是61616

1. 启动./activeMq start >myrunmq.log
2. 关闭./activeMq stop
3. 重启./activeMq restart
4. ps -ef|grep activemq|grep -v grep
5. netstat -anp|grep 61616
6. lsof -i:61616

## win访问linux中的mq

1. 关闭linux防火墙:service iptables stop
2. 关闭windows防火墙
3. linux中:
   1. 查看ip：ifconfig
   2. ping ip(windos地址)
4. windwos中：
   1. 查看ip：ipconfig
   2. ping ip(linux地址)
5. win中浏览器通过地址ip:8161可以访问activemq页面，默认用户名和密码为admin

## JMS开发的基本步骤

1. 创建一个connection factory
2. 通过connection factory创建JMS connection
3. 启动JMS connection
4. 通过connection创建JMS session
5. 创建JMS destination
6. 创建JMSproducer或者创建JMS message并设置destination
7. 创建JMS consumer或者注册一个JMS message listener
8. 发送或者接收JMS message(s)
9. 关闭所有的JMS资源(connection，session,producer,consumer等)

## 点对点消息传递域特点：

1. 每个消息只能有一个消费者，属于1:1的关系
2. 消息的生产者和消费者之间没有时间上的相关性
3. 消息被消费后队列中不会再存储，所以消费者不会消费到已经被消费掉的消息

## 发布/订阅消息传递域的特点

1. 生产者将消息发布到topic中，每个消息可以有多个消费者，属于1:N的关系
2. 生产者和消费者之间有时间上的相关性。订阅某一个主题的消费者只能消费自它订阅之后发布的消息
3. 生产者生产时，topic不保存消息它是无状态的不落地，假如无人订阅就去生产，那就是一条废消息，所有，一般先启动消费者再启动生产者。
4. JMS规范允许客户创建持久订阅，这在一定程度上放松了时间上的相关性要求。持久订阅允许消费者消费它在未处于激活状态时发送的消息。如：微信公众号订阅。

## Topic模式队列和Queue模式队列的区别

**Topic**：

1. 订阅-发布模式，如果当前没有订阅者，消息将会被丢弃，如果有多个订阅者，那么这些订阅者都会接收消息
2. 无状态
3. 消息按照订阅订阅者的数量进行复制，所有处理性能会随着订阅者的增加而明显降低，并且还要结合不同消息协议自身的性能差异

**Queue：**

1. 负载均衡模式，如果当前没有消费者，消息也不会丢弃；如果有多个消费者，那么一条消息也只会发送给其中一个消费者，并且要求消费者ack消息
2. Queue数据默认会在mq服务器上以文件形式保存，比如Active MQ一般保存在$AMQ_HOME\data\kr-store\data下面，也可以配置成DB存储
3. 由于一条消息只发送给一个消费者，所以就算消费者再多，性能也不会有明显降低，当然不同消息协议的具体性能也是有差异的

# JMS

## JMS组成的四大元素

1. JMS provider:实现JMS接口和规范的消息中间件，也是我们的MQ服务器
2. JMS producer：消息生产者，创建和发送JMS消息的客户端应用
3. JMS consumer：消息消费者，接收和处理JMS消息的客户端应用
4. JMS message：消息头，消息属性，消息体

### JMS message(重要)

#### 消息头

1. JMSDestination：消息发送的目的地，主要指Queue和Topic
2. JMSDeliveryMode:持久模式和非持久模式。
   1. 一条持久性的消息：应该被传送"一次仅仅一次"，如果JMS提供者出现故障，该消息并不会丢失，他会在服务器恢复之前再次传递。
   2. 一条非持久的消息：最多会传送一次，服务器出现故障，该消息将永远丢失。
3. JMSExpiration:消息的过期时间,默认是永不过期。
   1. 等同于Destination的send方法中的timeToLive值加上发送时刻的GTM时间值。
   2. 如果timeToLive值等于零，则JMSExpiration被设为零，表示该消息永不过期。
   3. 如果发送后，消息在规定时间内还没被发送到目的地，则该消息被清除。
4. JMSPriority:消息的优先级，从0-9十个级别，默认是4级。
   1. 0-4普通优先级，5-9是加急消息
   2. JMS不要求MQ严格按照这十个优先级发送消息，但必须保证加急消息要先于普通消息到达。
5. JMSMessageID:唯一识别每个消息的标识由MQ产生。

#### 消息体

封装具体的消息数据，发送和接收的消息体类型必须一致对应。

**五中消息体格式：**

1. TextMessage：普通字符串消息，包含一个string
2. MapMessage:一个Map类型的消息，key为string类型，值为Java的基本类型
3. BytesMessage:二进制数组消息，包含一个byte[]
4. StreamMessage:Java数据流消息，用标准流操作来顺序的填充和读取
5. ObjectMessage:对象消息，包含一个可序列化的Java对象

#### 消息属性

1. 如果需要除去消息头字段以外的值，那么可以使用消息属性。
2. 识别/去重/重点标注等操作非常有用的方法
3. 以属性名和属性值对(k-v)的形式制定的。

## JMS的可靠性

### PERSISTENT:持久性

1. 参数说明
   1. 持久：messageProducer.setDeliveryMode(DeliveryMode.PERSISTENT)。当服务器宕机，消息依然存在。
   2. 非持久：messageProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT).当服务器宕机，消息不存在。
   3. 默认持久。
2. 持久的Queue
   1. 持久化消息是队列的默认传送模式，保证这些消息只被传送一次和成功使用一次。对于这些消息，可靠性是优先考虑的因素。
   2. 可靠性的另一个重要方面是确保持久化消息被传送至目标后，消息服务在向消费者传送它们之前不会丢失这些消息。
3. 持久的Topic
   1. 先启动订阅再启动生产
   2. 持久的发布主题生产者
   3. 持久的订阅主题消费者
   4. 类似于微信公众号的订阅发布

### Transaction:事务

事务偏生产者/签收偏消费者

producer提交时的事务

1. false：只要执行send，就进入到队列中。关闭事务，那第二个签收参数的设置需要有效
2. true：先执行send再执行commit，消息才被真正的提交到队列中。消息需要批量发送，需要缓冲区处理

consumer接收时的事务

1. false：默认状态，只消费消息一次
2. true：开启了事务，必须需要commit，否则将会出现重复消费的情况。

### Acknowledge:签收

**非事务**

1. 自动签收(默认):Session.AUTO_ACKNOWLEDGE
2. 手动签收:Session.CLIENT_ACKNOWLEDGE,客户端调用acknowledge手动签收方法。message.acknowledge()
3. 允许重复消息：Session.DUPS_OK_ACKNOWLEDGE

**事务**

1. 生产事务开启，只有commit后才能将全部消息变为已消费消息

**签收和事务关系**

1. 在事务性会话中，当一个事务被成功提交则消息被自动签收。
2. 如果事务回滚，则消息会被再次传送
3. 非事务性会话中，消息何时被确认取决于创建会话时的应答模式(acknowledgement mode)
4. 事务开启，事务为主；事务关闭，签收为主

## 小结

### JMS的点对点总结

1. 点对点模型是基于队列的，生产者消息到队列，消费者从队列接收消息，队列的存在使得消息的异步传输成为可能。
2. 如果在Session关闭时有部分消息已被收到但还没有签收(acknowledge)，那当消费者下次连接到相同的队列时，这些消息还会被再次接收。
3. 队列可以长久地保存消息直到消费者接收到消息，消费者不需要因为担心消息会丢失而时刻和队列保持激活的连接状态，充分体现了异步传输模式的优势

### JMS的发布订阅总结

1. JMS Pub/Sub模式定义了如何向一个内容节点发布和订阅消息，这些节点被称为topic
2. 主题可以被认为是消息的传输中介，发布者(publisher)发布消息到主题，订阅者(subscribe)从主题订阅消息。
3. 主题使得消息订阅者和消息发布者保持互相独立，不需要接触即可保证消息的传送。

#### JMS非持久订阅

1. 非持久订阅只能当客户端处于激活状态，也就是和MQ保持连接状态才能收到发送到某个主题的消息
2. 如果消费者处于离线状态，生产者发送的主题消息将会丢失作废，消费者永远不会收到。
3. 一句话：先要订阅注册才能接收到发布，只给订阅者发布消息。

#### JMS持久订阅

1. 客户端首先向MQ注册一个自己的身份ID识别号。当这个客户端处于离线时，生产者会为这个ID保存所有发送到主题的消息，当客户再次连接到MQ时会根据消费者的ID得到所有当自己处于离线时发送到主题的消息
2. 非持久订阅状态不能恢复或重新派送一个未签收的消息
3. 持久订阅会恢复或重新派送一个未签收的消息

#### WHICH ONE？

当所有的消息必须被接收，则用持久订阅。当丢失消息能够被容忍，则用非持久订阅

# ActiveMQ的Broker

## Broker是什么？

1. 相当于一个ActiveMQ服务器实例
2. 说白了，Broker其实就是实现了用代码的形式启动ActiveMQ，将MQ嵌入到Java代码中，以便随时用随时启动。
3. 在用的时候再去启动这样能节省了资源，也保证了可靠性

## conf文件模拟启动不同实例

1. 拷贝一份activemq.xml的配置文件命名为activemq02.xml
2. 通过命令./activemq start xbean:file:/myactiveMQ/apache-activemq-5.15.9/conf/activemq02.xml 即启动了不同实例
3. lsof -i:61616

## 嵌入式Broker

1. 用ActiveMQ Broker作为独立的消息服务器来构建JAVA应用
2. ActiveMQ也支持在vm(虚拟机)中通信，基于嵌入式的broker，能够无缝的集成其它JAVA应用

### 配置

1. pom.xml

2. EnbedBroker(嵌入broker)

   ```java
   BrokerService brokerService=new BrokerService();
   brokerService.setUseJmx(true);
   brokerService.addConnector("tcp://localhost:61616");
   brokerService.start();
   ```

3. 队列验证

# Spring整合ActiveMQ

## pom.xml

```xml
<!-- activemq对JMS的支持，整合spring和activemq-->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-jms</artifactId>
            <version>4.3.23.RELEASE</version>
        </dependency>
<!-- activemq所需要的pool包配置-->
        <dependency>
            <groupId>org.apache.activemq</groupId>
            <artifactId>activemq-pool</artifactId>
            <version>5.15.9</version>
        </dependency>
```

## spring配置

```xml
<!--    开启包的自动扫描-->
    <context:component-scan base-package="com.lzq.mq.activemq.spring"></context:component-scan>

<!--    配置生产者-->
    <bean id="jmsFactory" class="org.apache.activemq.pool.PooledConnectionFactory" destroy-method="stop">
        <property name="connectionFactory">
<!-- 真正可以产生Connection的ConnectionFactory，由对应的JMS服务厂商提供-->
            <bean class="org.apache.activemq.ActiveMQConnectionFactory">
                <property name="brokerURL" value="tcp://47.101.133.169:61616"></property>
            </bean>
        </property>
        <property name="maxConnections" value="100"></property>
    </bean>
<!--    这个是队列目的地，点对点的-->
    <bean id="destinationQueue" class="org.apache.activemq.command.ActiveMQQueue">
        <constructor-arg index="0" value="spring-active-queue"></constructor-arg>
    </bean>
    <!--    这个是主题topic-->
    <!--<bean id="destinationTopic" class="org.apache.activemq.command.ActiveMQTopic">
        <constructor-arg index="0" value="spring-active-topic"></constructor-arg>
    </bean>-->

<!--    spring提供的JMS工具类，它可以进行消息发送、接收等-->
    <bean id="jmsTemplate" class="org.springframework.jms.core.JmsTemplate">
        <property name="connectionFactory" ref="jmsFactory"></property>
        <property name="defaultDestination" ref="destinationQueue"></property>
<!--        主题配置，代码无需修改，修改xml文件即可-->
<!--        <property name="defaultDestinationName" ref="destinationTopic"></property>-->
        <property name="messageConverter">
            <bean class="org.springframework.jms.support.converter.SimpleMessageConverter"></bean>
        </property>
    </bean>
<!--    配置监听程序-->
    <bean id="jmsContainer" class="org.springframework.jms.listener.DefaultMessageListenerContainer">
        <property name="connectionFactory" ref="jmsFactory"></property>
        <property name="destination" ref="destinationQueue"></property>
<!--public class MyMessageListener implements MessageListener-->
        <property name="messageListener" ref="myMessageListener"></property>
    </bean>
<!--    使用@Component注解，无需在此配置-->
<!--    <bean id="myMessageListener" class="com.lzq.mq.activemq.spring.MyMessageListener"></bean>-->
```

## 生产者和消费者

```java
@Service
public class ProducerAndConsumer {
    @Autowired
    private JmsTemplate jmsTemplate;
    @Test
    public void method_springMQ_producer() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        ProducerAndConsumer producerAndConsumer= (ProducerAndConsumer) context.getBean("producerAndConsumer");
        producerAndConsumer.jmsTemplate.send(session -> {
            TextMessage textMessage = session.createTextMessage("生产者：springMsgProducer");
            return textMessage;
        });
        System.out.println("生产完成**********");
    }
    @Test
    public void method_springMQ_consumer() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        ProducerAndConsumer producerAndConsumer= (ProducerAndConsumer) context.getBean("producerAndConsumer");
        String msg = (String) producerAndConsumer.jmsTemplate.receiveAndConvert();
        System.out.println(msg);
    }
}
```

# SpringBoot整合ActiveMQ

## pom.xml

```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-activemq</artifactId>
</dependency>
```

## application.yml

```properties
#activemq
spring:
  activemq:
    broker-url: tcp://47.101.133.169:61616 #自己的MQ服务器地址
    user: admin
    password: admin
  jms:
    pub-sub-domain: false #false=Queue  true= Topic 默认false

#自己定义队列Queue的名称
myqueue: boot-activemq-queue
```

## 队列



## 发布订阅