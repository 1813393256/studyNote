package com.lzq.designMode.creationalPattern.builderPattern.children.itemImpl.burgerExtend;

import com.lzq.designMode.creationalPattern.builderPattern.children.itemImpl.Burger;

/**
 * @Author laizhiqiang
 * @Description:建造者模式，创建扩展了Burger的实体类
 * @Date 2020/6/12 0012 14:46
 */
public class VegBurger extends Burger {
    @Override
    public String name() {
        return "Veg Burger";
    }

    @Override
    public float price() {
        return 25.0f;
    }
}
