package com.lzq.designMode.creationalPattern.builderPattern.father;

/**
 * @Author laizhiqiang
 * @Description:建造者模式，食物条目接口
 * @Date 2020/6/12 0012 14:08
 */
public interface Item {
    public String name();
    public Packing packing();
    public float price();
}
