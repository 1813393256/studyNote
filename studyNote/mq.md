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
    #pub-sub-domain: true #false=Queue  true= Topic 默认false
#自己定义队列Queue的名称
myqueue: boot-activemq-queue
#自定义topic名称
#mytopic: boot-activemq-topic
```

## 队列与发布订阅

```java
//ConfigBean类，用于加载yml文件中定义的名字，并装配queue
@Component
@EnableJms
public class ConfigBean {
    @Value("${myqueue}")
    private String myQueue;
    
    @Bean //<bean id="" class="">
    public Queue queue(){
        return new ActiveMQQueue(myQueue);
    }
    
  //  @Value("${mytopic}")
  //  private String mytopic;
    
     /*@Bean
    public Topic topic(){
        return new ActiveMQTopic(topicName);
    }*/
}
```

```java
//生产者
@Component
public class Queue_producer {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    @Autowired
    private Queue queue;
    /*@Autowired
    private Topic topic;*/
    
    public void produceMsg(){
        //参数修改为topic即可
        jmsMessagingTemplate.convertAndSend(queue,"******"+ UUID.randomUUID().toString().substring(0,6));
    }
    
    //间隔时间3秒钟定投，要求主启动类加@EnableScheduling
    @Scheduled(fixedDelay = 3000)
    public void produceMsgScheduled(){
 //参数修改为topic即可       
  jmsMessagingTemplate.convertAndSend(queue,"****Scheduled"+ UUID.randomUUID().toString().substring(0,6));
        System.out.println("*****produceMsgScheduled send ok");
    }
}
```

```java
//消费者
@Component
public class ConsumerService {
    //destination="${mytopic}"
    @JmsListener(destination = "${myqueue}")
    public void method_receive(TextMessage textMessage) throws JMSException {
        System.out.println("消费者收到消息**"+textMessage.getText());
    }
}
```

# ActiveMQ的传输协议

ActiveMQ支持的client-broker通信协议有：TCP，NIO，UDP，SSL，Http(s),VM

1. TCP:默认的协议，性能相对可以
2. NIO：基于TCP协议之上的，进行了扩展和优化，具有更好的扩展性
3. UDP：性能比TCP好，但是不具有可靠性
4. SSL：安全连接
5. HTTP(S):基于HTTP或HTTPS
6. VM：VM本身不是协议，当客户端和代理在同一个Java虚拟机(VM)中运行时，他们之间需要通信，但不想占用网络通道，而是直接通信，可以使用该方式。

## 传输协议配置

1. 修改conf/activemq.xml文件，添加以下配置即可

2. 地址：http://activemq.apache.org/configuring-transports

3. nio配置：

   ```xml
   <transportConnectors>
       <transportConnector name="nio" uri="nio://0.0.0.0:61616？trace=true"/>  
     </<transportConnectors>
   ```

4. auto+nio配置：自动检测+nio，5.13版本以上

   ```xml
   <transportConnector name="auto+nio" uri="auto+nio://0.0.0.0:61618?maximumConnections=1000&amp;wireFormat.maxFrameSize=104857600&amp;org.apache.activemq.transport.nio.SelectorManager.corePoolSize=20&amp;org.apache.activemq.transport.nio.SelectorManager.maximumPoolSize=50"/>
   ```

# ActiveMQ的消息存储和持久化

## 官网

1. 地址：http://activemq.apache.org/persistence

## 是什么?

1. 为避免意外宕机以后丢失数据，需要做到重启后可以恢复消息队列，消息系统一般都会采用持久化机制。
2. ActiveMQ的消息持久化机制有JDBC，AMQ，KahaDB和LevelDB，无论使用哪种持久化方式，消息的存储逻辑都是一致的
3. 发送者将消息发送出去后，消息中心首先将消息存储到本地数据文件，内存数据库或远程数据库等，再试图将消息发送给接收者，成功则将消息从存储中删除，失败则继续尝试发送。
4. 消息中心启动以后，首先要检查指定的存储位置，如果有未发送成功的消息，则需要把消息发送出去。

## 可靠性

1. activemq本身具有持久化，还支持可持久化机制(另一台服务器上存储有副本，不用担心MQ服务器宕机)。
2. activemq事务，持久，签收保证了消息的可靠性，同时其还支持可持久化

## 有哪些?

1. AMQ Message Store:基于文件的存储方式，是以前的默认消息存储，现在不用了。

2. KahaDB消息存储(默认):基于日志文件，从ActiveMQ5.4开始默认的持久化插件

3. JDBC消息存储：消息基于JDBD存储的

4. LevelDB消息存储：和KahaDB类似，基于文件的本地数据库存储形式，但它提供比KahaDB更快的持久性，不使用自定义B-Tree实现索引预写日志，而是使用基于LevelDB索引。默认配置如下：

   ```xml
   <persistenceAdapter>
       <levelDBdirectory="activemq-data"/>
   </persistenceAdapter>
   ```

   

5. JDBC Message Store with ActiveMQ Journal

## KahaDB

1. 官网: http://activemq.apache.org/kahadb
2. KahaDB是目前默认的存储方式，可用于任何场景，提高了性能和恢复能力
3. 消息存储使用一个事务日志和仅仅用一个索引文件来存储它所有的地址
4. KahaDB是一个专门针对消息持久化的解决方案，它对典型的消息使用模式进行了优化
5. 数据被追加到data logs中。当不再需要log文件中的数据时，log文件会被丢弃

**存储文件介绍**

1. db<Number>.log,存储消息到预定义大小的数据记录文件中，文件命名为db<Number>.log,当数据文件已满时，一个新的文件会随着创建，number数值也会随之递增，它随着消息数量的增多，如每32M一个文件，文件名按照数字进行编号，如db-1.log，db-2.log...。当不再有引用到数据文件中的任何消息时，文件会被删除或归档
2. db.data该文件包含了持久化的BTree索引，索引了消息数据记录中的消息，它是消息的索引文件，本质上是B-Tree(B树),使用B-Tree作为索引指向db-<Number>.log里面存储的信息
3. db.free当前db.data文件里那些页面是空闲的，文件具体内容是所有空闲页的ID
4. db.redo用来进行消息恢复，如果KahaDB消息存储在强制退出后启动，用于恢复BTree索引
5. lock文件锁，表示当前获得Kahadb读写权限的broker。

## JDBC消息存储

1. MQ+MySQL

2. 配置地址:  http://activemq.apache.org/persistence

3. 添加mysql数据库的驱动包到activemq的lib文件夹

4. jdbcPersistenceAdapter配置:

   ```xml
   <!-- broker 标签内 -->
   <persistenceAdapter> 
    <!--createTablesOnStartup 刚启动就创建表，首次启动true，以后请设置为false，默认true-->
     <jdbcPersistenceAdapter dataSource="#mysql-ds" createTablesOnStartup="true"/> 
   </persistenceAdapter>
   ```

   ```xml
   <!-- 放在broker标签外 -->
   <bean id="mysql-ds" class="org.apache.commons.dbcp2.BasicDataSource" destroy-method="close"> 
       <property name="driverClassName" value="com.mysql.jdbc.Driver"/> 
       <property name="url" value="jdbc:mysql://localhost:3306/activemq?relaxAutoCommit=true"/> 
       <property name="username" value="root"/> 
       <property name="password" value="123456"/> 
       <property name="maxTotal" value="200"/>
       <property name="poolPreparedStatements" value="true"/> 
     </bean> 
   ```

5. 建仓SQL和建表

   1. 创建activemq数据库

   2. ACTIVEMQ_MSGS:

      ```properties
      消息表，queue和topic都存在里面
      ID:自增的数据库主键
      CONTAINER:消息的Destination
      MSGID_PROD:消息发送者的主键
      MSG_SEQ:发送消息的顺序，MSGID_PROD+MSG_SEQ可以组成JMS的MessageID
      EXPIRATION:消息的过期时间，存储的是从1970-01-01到现在的毫秒数
      MSG:消息本体的Java序列化对象的二进制数据
      PRIORITY:优先级，从0-9，数值越大优先级越高
      ```

   3. ACTIVEMQ_ACKS:

      ```properties
      用于存储订阅关系。如果是持久化Topic，订阅者和1服务器的订阅关系在这个表保存。
      存储持久订阅的信息和最后一个持久订阅接收的消息ID
      CONTAINER:消息的Destination
      SUB_DEST:如果是使用Static集群，这个字段会有集群其他系统的消息
      CLIENT_ID:每个订阅者都必须有一个唯一的客户端ID用于区分
      SUB_NAME:订阅者名称
      SELECTOR:选择器，可以选择只消费满足条件的消息。条件可以用自定义属性实现，可支持多属性AND和OR操作
      LAST_ACKED_ID:记录消费过的消息的ID。
      ```

      

   4. ACTIVEMQ_LOCK:

      ```properties
      在集群环境中才有用，只有一个Broker可以获得消息，称为Master Broker,其他的只能作为备份等待Master Broker不可用，才可能成为下一个Master Broker。这个表记录那个Broker是当前的Master Broker。
      ID:唯一的锁id
      Broker Name：拥有锁的ActiveMQ的名字
      ```

6. 代码运行验证

   1. 点对点类型中，当DeliveryMode设置为NON_PERSISTENCE时，消息被保存在内存中；当DeliveryMode设置为PERSISTENCE时，消息保存在broker的相应的文件或数据库中。并且消息一旦被Consumer消费就从broker中删除。
   2. 发布订阅中，消费者启动，持久化时，会把消费者信息存入ack表中，生产者发布的消息会存入msg表中。

7. 小总结

   1. queue，在没有消费者消费的情况下，会将消息保存到activemq_msgs表中，只要有任意一个消费者已经消费过了，消费之后这些消息会立即被删除。
   2. topic，一般是先启动消费订阅然后再生产的情况下，会将消息保存到activemq_acks。

8. 开发有坑

   1. 在配置关系型数据库作为ActiveMQ的持久化存储方案时，有坑
   2. 数据库jar包：将需要使用到的相关jar文件放置到ActiveMQ安装路劲下的lib目录。mysql-jdbc驱动的jar包和对应的数据库连接池jar包
   3. createTablesOnStartup属性：在jdbcPersistenceAdapter标签中设置了createTablesOnStartup属性为true时，在第一次启动ActiveMQ时，ActiveMQ服务节点会自动创建所需要的数据表，启动完成后可以去掉这个属性，或者更改createTablesOnStartup属性为false。
   4. 下划线坑爹："java.lang.illegalStateException:BeanFactory not initialized or already closed"这是因为您的操作系统的机器名中有"_"符号，请更改机器名并且重启后即可解决问题。

## JDBC+Journal

**xml配置**

```xml
<persistenceFactory>
	<journalPersistenceAdapterFactory 
    	journalLogFiles="4"
        journalLogFileSize="32768"
        useJournal="true"
        useQuickJournal="true"
        dataSource="#mysql-ds"
        dataDirectory="activemq-data"/> 
</persistenceFactory>
```

**数据库配置**

```xml
<!-- 放在broker标签外 -->
<bean id="mysql-ds" class="org.apache.commons.dbcp2.BasicDataSource" destroy-method="close"> 
    <property name="driverClassName" value="com.mysql.jdbc.Driver"/> 
    <property name="url" value="jdbc:mysql://localhost:3306/activemq?relaxAutoCommit=true"/> 
    <property name="username" value="root"/> 
    <property name="password" value="123456"/> 
    <property name="maxTotal" value="200"/>
    <property name="poolPreparedStatements" value="true"/> 
  </bean> 
```

1. 这种方式克服了JDBC Store的不足，JDBC每次消息过来，都需要去写库和读库。
2. ActiveMQ Journal，使用高速缓存写入技术，大大提高了性能。
3. 当消费者的消费速度能够及时跟上生产者消息的生成速度时，journal文件能够大大减少需要写入到DB中的消息。
4. 示例：生产者生产了1000条消息，这1000条消息会保存到journal文件，如果消费者的消费速度很快的情况下，在journal文件还没有同步到DB之前，消费者已经消费了90%以上的消息，那么这个时候只需要同步剩余的10%的消息到DB，如果消费者的消费速度很慢，者个时候journal文件可以使消息以批量方式写到DB。

## ActiveMQ持久化机制小总结

**持久化消息**：MQ所在的服务器down了消息不会丢失的机制

**持久化机制演化过程:**从最初的AMQ Message Store方案到ActiveMQ v4版本中推出的High performance journal(高性能事务支持)附件，并且同步推出了关于关系型数据库的存储方法，ActiveMQ 5.3 版本中又推出了对KahaDB的支持(v5.4版本后称为ActiveMQ默认的持久化方案)，后来ActiveMQ v5.8版本喀什支持LevelDB，到现在，v5.9+版本提供了标准的Zookeeper+LevelDB集群化方案。

**ActiveMQ的消息持久化机制有：**

1. AMQ：基于日志文件
2. KahaDB：基于日志文件，从ActiveMQ 5.4开始默认的持久化插件
3. JDBC：基于第3放数据库
4. LevelDB：基于文件的本地数据库存储，从ActiveMQ5.8版本之后又推出了LevelDB的持久化引擎性能高于KahaDB
5. Replicated LevelDB Store：从ActiveMQ5.9提供了基于LevelDB和Zookeeper的数据复制方式，用于Master-slave方式的首选数据复制方案。
6. 无论使用哪种持久化方式，消息存储逻辑都是一致的。

# ActiveMQ多节点集群

## 面试题

1. 引入消息队列后如何保证其高可用性

## 是什么？

1. 基于Zookeeper和LevelDB搭建ActiveMQ集群。
2. 集群仅提供主备方式的高可用集群功能，避免单点故障。

## 主从集群

**Zookeeper+replicated-leveldb-store的主从集群**

1. 避免单点故障

2. 原理说明

   ```markdown
   1. 使用Zookeeper集群注册所有的ActiveMQ Broker,但只有其中的一个Broker可以提供服务，他被视为Master，其他的Broker处于待机状态，被视为Slave。
   2. 如果Master因故障而不能提供服务，Zookeeper会从Slave中选举一个Broker充当Master。
   3. Slave连接Master并同步他们的存储状态，Slave不接受客户端连接。所有的存储操作都将会被复制到连接至Master的Slaves。
   4. 如果Master宕机，得到了最新更新的Slave会成为Master。
   5. 故障节点在恢复后会重新加入到集群中并连接Master进入Slave模式
   6. 所有需要同步的消息操作都将等待存储状态被复制到其他法定节点的操作完成才能完成。
   7. 如果配置了replicas=3，那么法定大小是(3/2)+1=2。Master将会存储并更新，然后等待(2-1)=1个Slave存储和更新完成，才汇报success。
   8. 有一个node要作为观察者存在。当一个新的Master被选中，你需要至少保障一个法定node在线以能够找到拥有最新状态的node。这个node才可以成为最新的Master。
   9. 因此，推荐运行至少3个replica nodes以防止一个node失败后服务中断。
   ```

## 部署规划和步骤

1. 环境和版本
2. 关闭防火墙并保证win可以ping通ActiveMQ服务器
3. 要求具备ZK集群并可以成功启动
4. 集群部署规划列表
5. 创建3台集群目录
6. 修改管理控制台端口
7. hostname名字映射
8. ActiveMQ集群配置
9. 修改各节点的消息端口
10. 按顺序启动3个ActiveMQ节点，前提：zk集群已经成功启动运行
11. zk集群的节点状态说明

# 高级特性和大厂常考重点

## 引入消息队列之后该如何保证其高可用性

```markdown
持久化，事物，签收，zookeeper+replicated-leveldb-store的主从集群
```

## 异步投递Async Sends

1. 对于一个Slow Consumer，使用同步发送信息可能出现Producer堵塞等情况，慢消费者适合异步发送。
2. ActiveMQ支持同步、异步两种发送的模式将小发送到broker，模式的选择对发送延迟有巨大的影响。producer能达到怎样的产出率(产出率=发送数据总量/时间)主要受发送延时的影响，使用异步发送可以显著的提高发送的性能。
3. **ActiveMQ默认使用异步发送的模式**：除非**明确指定使用同步发送的方式**或者在**未使用事物的前提下发送持久化的消息**，这两种情况都是**同步**发的。
4. 如果你没有使用事物且发送的是持久化的消息，每一次发送都是同步发送的且会阻塞producer直到broker返回一个确认，表示消息已经被安全的持久化到磁盘。确认机制提供了消息安全的保障，但同时会阻塞客户端，带来了很大的延时。
5. 很多高性能的应用，允许在失败的情况下有少量的数据丢失。如果你的应用满足这个特点，可以使用异步发送来提高生产率，即使发送的是持久化的消息。

```java
//异步投递开启方法：
1. tcp://localhost:61616?jms.useAsyncSend=true
2. ((ActiveMQConnectionFactory)connectionFactory).setUseAsyncSend(true);
3. ((ActiveMQConnection)connection).setUseAsyncSend(true)

//异步发送如何确保发送成功，部分关键代码
ActiveMQConnectionFactory factory=new ActiveMQConnectionFactory(ACTIVEMQ_URL);
				factory.setUseAsyncSend(true);
ActiveMQMessageProducer activeMQMessageProducer = (ActiveMQMessageProducer)session.createProducer(queue);
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
```

**异步发送**

```markdown
1. 它可以最大化producer端的发送效率。我们通常在发送消息量比较密集的情况下使用异步发送，它可以很大的提升Producer性能；不过这也带来了额外的问题：
2. 就是需要消耗较多的client端内存，同时会导致broker端性能消耗增加；
3. 他不能有效的确保消息的发送成功。在useAsyncSend=true的情况下，客户端需要容忍消息丢失的可能。
```

## 延迟投递和定时投递

1. AMQ_SCHEDULED_DELAY:long	延迟投递的时间

2. AMQ_SCHEDULED_PERIOR:long	重复投递的时间间隔

3. AMQ_SCHEDULED_REPEAT:int    重复投递次数

4. AMQ_SCHEDULED_CRON:String    Cron表达式

5. activemq.xml配置

   ```xml
   //要在activemq.xml中配置schedulerSupport属性为true
   <broker xmlns="http://activemq.apache.org/schema/core" brokerName="localhost" dataDirectory="${activemq.data}" schedulerSupport="true">
   ```

6. java代码

   ```java
   //java代码里面封装的辅助消息类型：ScheduledMessage
   //代码：
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
   ```

## 分发策略

...........................................................目前没有.................................

## ActiveMQ消费重试机制

**具体哪些情况会引起消息重发？**

1. Client用了transactions且在session中调用了rollback()
2. Client用了transactions且在调用commit()之前关闭或者没有commit
3. Client在CLIENT_ACKNOWLEDGE的传递模式下，在session中调用了recover()

**请说说消息重发时间间隔和重构发次数吗？**

1. 间隔：1s
2. 次数：6

**有毒消息Poison ACK谈谈你的理解**

1. 一个消息被redelivedred超过默认的最大重发次数(默认6次)时，消费端会给MQ发送一个“poison ack”表示这个消息有毒，告诉broker不要再发了，这个时候broker会把这个消息放到DLQ(死信队列)。

**属性：**

1. backOffMultiplier：重连时间间隔递增倍数，只有值大于1和启用useExponentialBackOff参数时才生效，默认为5
2. collisionAvoidanceFactor：设置防止冲突范围的正负百分比，只有启用useCollisionAvoidance参数时才生效。也就是再延迟时间上再加一个时间波动范围。默认值为0.15
3. initialRedeliveryDelay：初始重发延迟时间，默认1000L
4. maximumRedeliveries：最大重传次数，达到最大重连次数后抛出异常，为-1时不限制次数，为0时表示不进行重传。默认值为6
5. maximumRedeliveryDelay：最大传送延迟，只在useExponentialBackOff为true时有效(v5.5)，假设首次重连间隔为10ms，倍数为2，那么第二次重连时间间隔为20ms，第三次重连时间间隔为40ms，当重连时间间隔大于最大重连时间间隔时，以后每次重连时间间隔都为最大重连时间间隔。默认为-1。
6. redeliveryDelay：重发延迟时间，当initialRedeliveryDelay=0时生效，默认为1000L
7. useCollisionAvoidance：启用防止冲突功能，默认false
8. useExponentialBackOff：启用指数倍数递增的方式增加延迟时间，默认false。

## 死信队列(DLQ)

1. ActiveMQ中引入了“死信队列”（Dead Letter Queue）的概念。即一条消息再被重发了多次后(默认为6次redeliveryCounter==6)，将会被ActiveMQ移入"死信队列"。开发人员可以在这个Queue中查看出错的信息。进行人工干预。
2. 处理失败的消息
3. 尚硅谷p67

## 如何保证消息不被重复消费？幂等性问题你谈谈

网络延迟传输中，会造成进行MQ重试，在重试过程中，可能会造成重复消费。

**解决方案：**

1. 如果消息是做数据库的插入操作，给这个消息做一个唯一主键，那么就算出现重复消费的情况，就会导致主键冲突，避免数据库出现脏数据。
2. 设置token
3. 如果上面两种情况还不行，准备一个第三方服务方来做消费记录，以redis为例，给消息分配一个全局id，只有消费过该消息，将<id,message>以K-V形式写入redis。那消费者开始消费前，先去redis中查询有没有消费记录即可。

