package com.lzq.designMode.structuralPattern.bridgePattern.children;

import com.lzq.designMode.structuralPattern.bridgePattern.father.DrawAPI;

/**
 * @Author laizhiqiang
 * @Description:桥接模式，创建实现了DrawAPI接口的实体桥接实现类
 * @Date 2020/6/16 0016 11:28
 */
public class RedCircle implements DrawAPI {
    @Override
    public void drawCircle(int radius, int x, int y) {
        System.out.println("[Color:red,radius:"+radius+",x:"+x+",y:"+y+"]");
    }
}
