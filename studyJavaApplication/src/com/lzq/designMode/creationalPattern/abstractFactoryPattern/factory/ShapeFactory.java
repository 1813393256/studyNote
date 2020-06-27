package com.lzq.designMode.creationalPattern.abstractFactoryPattern.factory;

import com.lzq.designMode.creationalPattern.abstractFactoryPattern.children.Rectangle;
import com.lzq.designMode.creationalPattern.abstractFactoryPattern.children.Square;
import com.lzq.designMode.creationalPattern.abstractFactoryPattern.father.Color;
import com.lzq.designMode.creationalPattern.abstractFactoryPattern.father.Shape;

/**
 * @Author laizhiqiang
 * @Description:继承AbstractFactory类,实现子类实例的创建
 * @Date 2020/6/12 0012 10:53
 */
public class ShapeFactory extends AbstractFactory{
    @Override
    public Color getColor(String color) {
        return null;
    }

    @Override
    public Shape getShape(String shapeType) {
        if (shapeType==null){
            return null;
        }
        if (shapeType.equalsIgnoreCase("RECTANGLE")){
            return new Rectangle();
        }
        else if (shapeType.equalsIgnoreCase("Square")){
            return new Square();
        }
        return null;
    }
}
