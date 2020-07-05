package com.lzq.activemq;

import com.lzq.ProducerApplication;
import com.lzq.activemq.service.Queue_producer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

/**
 * @Author laizhiqiang
 * @Description: boot-activemq测试类
 * @Date 2020/7/5 0005 11:54
 */
@SpringBootTest(classes = ProducerApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class TestActiveMQ {
    @Resource
    private Queue_producer queue_producer;

    @Test//测试com.lzq.activemq.service.Queue_producer.produceMsg方法
    public void produceMsg(){
        queue_producer.produceMsg();
    }

    @Test//测试类会发送两次
    public void produceMsgScheduled(){
        queue_producer.produceMsgScheduled();
    }
}
