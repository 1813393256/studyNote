package com.lzq.designMode.creationalPattern.abstractFactoryPattern.factory;

/**
 * @Author laizhiqiang
 * @Description:工厂生产类，创建一个工厂创造器/生成器类，通过传递形状或颜色信息来获取工厂。
 * @Date 2020/6/12 0012 11:01
 */
public class FactoryProducer {
    public static AbstractFactory getFactory(String choiceFactory){
        if (choiceFactory.equalsIgnoreCase("shape")){
            return new ShapeFactory();
        }else if (choiceFactory.equalsIgnoreCase("color")){
            return new ColorFactory();
        }
        return null;
    }
}
