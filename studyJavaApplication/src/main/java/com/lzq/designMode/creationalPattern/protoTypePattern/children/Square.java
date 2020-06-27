package com.lzq.designMode.creationalPattern.protoTypePattern.children;

import com.lzq.designMode.creationalPattern.protoTypePattern.father.Shape;

/**
 * @Author laizhiqiang
 * @Description:原型模式，创建扩展了Shape抽象类的实体类
 * @Date 2020/6/12 0012 15:46
 */
public class Square extends Shape {
    public Square() {
        type="Square";
    }
    @Override
    public void draw() {
        System.out.println("Square Draw()");
    }
}
