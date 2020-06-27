package com.lzq.designMode.structuralPattern.adapterPattern.children;

import com.lzq.designMode.structuralPattern.adapterPattern.father.AdvancedMediaPlayer;

/**
 * @Author laizhiqiang
 * @Description:创建实现了 AdvancedMediaPlayer 接口的实体类。
 * @Date 2020/6/12 0012 16:32
 */
public class VlcPlayer implements AdvancedMediaPlayer {
    @Override
    public void playVlc(String fileName) {
        System.out.println("playVlc:"+fileName);
    }

    @Override
    public void playMp4(String fileName) {
        //什么也不做
    }
}
