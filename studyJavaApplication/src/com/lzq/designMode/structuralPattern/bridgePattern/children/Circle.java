package com.lzq.designMode.structuralPattern.bridgePattern.children;

import com.lzq.designMode.structuralPattern.bridgePattern.father.DrawAPI;
import com.lzq.designMode.structuralPattern.bridgePattern.father.Shape;

/**
 * @Author laizhiqiang
 * @Description:桥接模式，创建实现了 Shape 接口的实体类。
 * @Date 2020/6/16 0016 11:32
 */
public class Circle extends Shape {
    private int x,y,radius;
    public Circle(int x, int y, int radius, DrawAPI drawAPI){
        super(drawAPI);
        this.x = x;
        this.y=y;
        this.radius=radius;
    }
    @Override
    public void draw() {
        drawAPI.drawCircle(radius,x,y);
    }
}
