package com.lzq.designMode.creationalPattern.protoTypePattern;

import com.lzq.designMode.creationalPattern.protoTypePattern.father.Shape;

/**
 * @Author laizhiqiang
 * @Description:原型模式，PrototypePatternDemo 使用 ShapeCache 类来获取存储在 Hashtable 中的形状的克隆。
 * @Date 2020/6/12 0012 16:05
 */
public class ProtoTypePatternDemo {
    public static void main(String...args){
        ShapeCache.loadCache();
        Shape shape = ShapeCache.getShape("1");
        System.out.println("Shape:"+shape.getType());

        Shape shape1 = ShapeCache.getShape("2");
        System.out.println("Shape:"+shape1.getType());

        Shape shape2 = ShapeCache.getShape("3");
        System.out.println("Shape:"+shape2.getType());
    }
}
