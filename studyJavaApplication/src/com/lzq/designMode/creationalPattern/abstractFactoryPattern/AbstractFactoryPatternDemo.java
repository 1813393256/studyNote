package com.lzq.designMode.creationalPattern.abstractFactoryPattern;

import com.lzq.designMode.creationalPattern.abstractFactoryPattern.factory.AbstractFactory;
import com.lzq.designMode.creationalPattern.abstractFactoryPattern.factory.FactoryProducer;
import com.lzq.designMode.creationalPattern.abstractFactoryPattern.father.Color;
import com.lzq.designMode.creationalPattern.abstractFactoryPattern.father.Shape;

/**
 * @Author laizhiqiang
 * @Description:抽象工厂模式
 * @Date 2020/6/12 0012 11:05
 */
public class AbstractFactoryPatternDemo {
    public static void main(String...args){
        //选择工厂
        AbstractFactory abstractFactory= FactoryProducer.getFactory("shape");
        AbstractFactory abstractFactory1=FactoryProducer.getFactory("color");
        //获取单个工厂实例
        Shape rectangle = abstractFactory.getShape("rectangle");
        Color red = abstractFactory1.getColor("red");
        //画出rectangle矩形
        rectangle.draw();
        red.fill();

        //获取单个工厂实例
        Shape square = abstractFactory.getShape("square");
        Color blue = abstractFactory1.getColor("blue");
        //画出square矩形
        square.draw();
        blue.fill();


    }
}
