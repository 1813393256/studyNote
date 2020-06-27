package com.lzq.designMode.creationalPattern.abstractFactoryPattern.children;

import com.lzq.designMode.creationalPattern.abstractFactoryPattern.father.Color;

/**
 * @Author laizhiqiang
 * @Description:抽象工厂模式，实现Color接口的类
 * @Date 2020/6/12 0012 10:48
 */
public class Red implements Color {
    @Override
    public void fill() {
        System.out.println("Color：red");
    }
}
