package com.lzq.designMode.structuralPattern.bridgePattern;

import com.lzq.designMode.structuralPattern.bridgePattern.children.Circle;
import com.lzq.designMode.structuralPattern.bridgePattern.children.GreenCircle;
import com.lzq.designMode.structuralPattern.bridgePattern.children.RedCircle;
import com.lzq.designMode.structuralPattern.bridgePattern.father.Shape;

/**
 * @Author laizhiqiang
 * @Description:桥接模式，在有多种可能会变化的情况下，用继承会造成类爆炸问题，扩展起来不灵活。
 * @Date 2020/6/15 0015 11:47
 */
public class BridgePatternDemo {
    public static void main(String...args){
        Shape redCircle = new Circle(100, 100, 10, new RedCircle());
        Shape greenCircle = new Circle(100, 100, 10, new GreenCircle());
        redCircle.draw();
        greenCircle.draw();
    }
}
