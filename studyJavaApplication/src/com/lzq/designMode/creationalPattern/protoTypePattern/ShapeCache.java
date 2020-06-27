package com.lzq.designMode.creationalPattern.protoTypePattern;


import com.lzq.designMode.creationalPattern.protoTypePattern.children.Circle;
import com.lzq.designMode.creationalPattern.protoTypePattern.children.Rectangle;
import com.lzq.designMode.creationalPattern.protoTypePattern.children.Square;
import com.lzq.designMode.creationalPattern.protoTypePattern.father.Shape;

import java.util.Hashtable;

/**
 * @Author laizhiqiang
 * @Description:原型模式，从数据库获取实体类，并把他们存储在一个Hashtable中
 * @Date 2020/6/12 0012 15:51
 */
public class ShapeCache {
    private static Hashtable<String, Shape>shapeMape=new Hashtable<>();
    public static Shape getShape(String shapeId){
        Shape cachedShape = shapeMape.get(shapeId);
        return (Shape) cachedShape.clone();
//        return cachedShape;
    }

    //对每种形状都运行数据库查询，并创建该形状
    //shapeMap.put(shapeKey,shape)
    //例如，我们要添加三种形状
    public static void loadCache(){
        Circle circle = new Circle();
        circle.setId("1");
        shapeMape.put(circle.getId(),circle);

        Square square = new Square();
        square.setId("2");
        shapeMape.put(square.getId(),square);

        Rectangle rectangle = new Rectangle();
        rectangle.setId("3");
        shapeMape.put(rectangle.getId(),rectangle);
    }
}
