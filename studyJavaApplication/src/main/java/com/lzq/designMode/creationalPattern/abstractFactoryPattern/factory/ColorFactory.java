package com.lzq.designMode.creationalPattern.abstractFactoryPattern.factory;

import com.lzq.designMode.creationalPattern.abstractFactoryPattern.children.Blue;
import com.lzq.designMode.creationalPattern.abstractFactoryPattern.children.Red;
import com.lzq.designMode.creationalPattern.abstractFactoryPattern.father.Color;
import com.lzq.designMode.creationalPattern.abstractFactoryPattern.father.Shape;

/**
 * @Author laizhiqiang
 * @Description:继承AbstractFactory类,实现子类实例的创建
 * @Date 2020/6/12 0012 10:57
 */
public class ColorFactory extends AbstractFactory{
    @Override
    public Color getColor(String color) {
        if (color==null){
            return null;
        }
        if (color.equalsIgnoreCase("red")){
            return new Red();
        }else if (color.equalsIgnoreCase("blue")){
            return new Blue();
        }
        return null;
    }

    @Override
    public Shape getShape(String shapeType) {
        return null;
    }
}
