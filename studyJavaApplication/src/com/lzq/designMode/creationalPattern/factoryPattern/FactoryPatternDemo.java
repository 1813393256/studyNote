package com.lzq.designMode.creationalPattern.factoryPattern;

import com.lzq.designMode.creationalPattern.factoryPattern.factory.ShapeFactory;
import com.lzq.designMode.creationalPattern.factoryPattern.father.Shape;

/**
 * @Author laizhiqiang
 * @Description:使用该工厂，通过传递类型信息来获取实体类的对象
 * @Date 2020/6/12 0012 10:21
 * 注：由于子类重写了父类的方法，所以父类的引用调用方法将会调用到子类的方法
 */
public class FactoryPatternDemo {
    public static void main(String...args){
        //创建工厂实例对象
        ShapeFactory shapeFactory=new ShapeFactory();
        //获取Rectangle对象实例
        Shape shape = shapeFactory.getShape("RECTANGLE");
        //调用Rectangle中实现的方法
        shape.draw();

        //获取Square对象实例
        Shape shape1 = shapeFactory.getShape("Square");
        //调用Square中实现的方法
        shape1.draw();
    }
}
