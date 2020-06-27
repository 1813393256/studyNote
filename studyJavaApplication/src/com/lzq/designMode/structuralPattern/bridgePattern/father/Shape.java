package com.lzq.designMode.structuralPattern.bridgePattern.father;

/**
 * @Author laizhiqiang
 * @Description:桥接模式，使用 DrawAPI 接口创建抽象类 Shape。
 * @Date 2020/6/16 0016 11:31
 */
public abstract class Shape {
    protected DrawAPI drawAPI;
    protected Shape(DrawAPI drawAPI){
        this.drawAPI=drawAPI;
    }
    public abstract void draw();
}
