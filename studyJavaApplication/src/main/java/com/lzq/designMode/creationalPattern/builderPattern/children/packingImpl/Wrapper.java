package com.lzq.designMode.creationalPattern.builderPattern.children.packingImpl;

import com.lzq.designMode.creationalPattern.builderPattern.father.Packing;

/**
 * @Author laizhiqiang
 * @Description:建造者模式,实现Packing接口的实体类
 * @Date 2020/6/12 0012 14:11
 */
public class Wrapper implements Packing {
    @Override
    public String pack() {
        //食物套餐
        return "Wrapper";
    }
}
