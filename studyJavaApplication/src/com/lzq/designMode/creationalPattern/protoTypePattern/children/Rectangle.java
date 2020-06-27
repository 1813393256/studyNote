package com.lzq.designMode.creationalPattern.protoTypePattern.children;

import com.lzq.designMode.creationalPattern.protoTypePattern.father.Shape;

/**
 * @Author laizhiqiang
 * @Description:原型模式，创建扩展了Shape抽象类的实体类
 * @Date 2020/6/12 0012 15:41
 */
public class Rectangle extends Shape {
    public Rectangle(){
        type="Rectangle";
    }
    @Override
    public void draw(){
        System.out.println("Rectangle Draw()");
    }
}
