package com.lzq.designMode.creationalPattern.builderPattern.children.itemImpl.coldDrinkExtend;

import com.lzq.designMode.creationalPattern.builderPattern.children.itemImpl.ColdDrink;

/**
 * @Author laizhiqiang
 * @Description:建造者模式，创建扩展了ColdDrink的实体类
 * @Date 2020/6/12 0012 14:50
 */
public class Coke extends ColdDrink {
    @Override
    public String name() {
        return "Coke";
    }

    @Override
    public float price() {
        return 30.0f;
    }
}
