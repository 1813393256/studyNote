package com.lzq.designMode.creationalPattern.factoryPattern.factory;

import com.lzq.designMode.creationalPattern.factoryPattern.children.Rectangle;
import com.lzq.designMode.creationalPattern.factoryPattern.children.Square;
import com.lzq.designMode.creationalPattern.factoryPattern.father.Shape;

/**
 * @Author laizhiqiang
 * @Description:工厂模式，创建一个工厂类，生成基于给定信息的实例类对象
 * @Date 2020/6/12 10:17
 */
public class ShapeFactory {
    public Shape getShape(String shapeType){
        if (shapeType==null){
            return null;
        }
        if (shapeType.equalsIgnoreCase("RECTANGLE")){
            return new Rectangle();
        }else if (shapeType.equalsIgnoreCase("SQUARE")){
            return new Square();
        }
        return null;
    }
}
