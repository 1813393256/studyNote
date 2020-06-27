package com.lzq.designMode.creationalPattern.factoryPattern.children;

import com.lzq.designMode.creationalPattern.factoryPattern.father.Shape;

/**
 * @Author laizhiqiang
 * @Description:工厂模式，定义实现Shape接口的类
 * @Date 2020/6/12 10:13
 */
public class Square implements Shape {
    @Override
    public void draw() {
        System.out.println("Square实现Shape接口的类");
    }
}
