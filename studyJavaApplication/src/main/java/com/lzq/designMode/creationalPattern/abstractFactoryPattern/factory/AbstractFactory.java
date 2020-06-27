package com.lzq.designMode.creationalPattern.abstractFactoryPattern.factory;

import com.lzq.designMode.creationalPattern.abstractFactoryPattern.father.Color;
import com.lzq.designMode.creationalPattern.abstractFactoryPattern.father.Shape;

/**
 * @Author laizhiqiang
 * @Description:抽象工厂
 * @Date 2020/6/12 0012 10:51
 */
public abstract class AbstractFactory {
    public abstract Color getColor(String color);
    public abstract Shape getShape(String shapeType);
}
