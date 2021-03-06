package com.lzq.designMode.creationalPattern.builderPattern.children.itemImpl;

import com.lzq.designMode.creationalPattern.builderPattern.children.packingImpl.Bottle;
import com.lzq.designMode.creationalPattern.builderPattern.father.Item;
import com.lzq.designMode.creationalPattern.builderPattern.father.Packing;

/**
 * @Author laizhiqiang
 * @Description:建造者模式，实现Item接口的抽象类，提供默认的功能
 * @Date 2020/6/12 0012 14:36
 */
public abstract class ColdDrink implements Item {
    @Override
    public Packing packing() {
        return new Bottle();
    }

    @Override
    public abstract float price();
}
