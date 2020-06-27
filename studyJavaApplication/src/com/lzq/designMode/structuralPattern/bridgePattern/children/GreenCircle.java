package com.lzq.designMode.structuralPattern.bridgePattern.children;

import com.lzq.designMode.structuralPattern.bridgePattern.father.DrawAPI;

/**
 * @Author laizhiqiang
 * @Description:创建实现了DrawAPI接口的实体桥接实现类
 * @Date 2020/6/15 0015 12:05
 */
public class GreenCircle implements DrawAPI {
    @Override
    public void drawCircle(int radius, int x, int y) {
        System.out.println("[Color:green,radius:"+radius+",x:"+x+",y:"+y+"]");
    }
}
