package com.lzq.designMode.creationalPattern.builderPattern.children.itemImpl.coldDrinkExtend;

import com.lzq.designMode.creationalPattern.builderPattern.children.itemImpl.ColdDrink;

/**
 * @Author laizhiqiang
 * @Description:建造者模式，创建扩展了ColdDrink的实体类
 * @Date 2020/6/12 0012 14:54
 */
public class Pepsi extends ColdDrink {

    @Override
    public String name() {
        return "Pepsi";
    }

    @Override
    public float price() {
        return 35.0f;
    }
}
