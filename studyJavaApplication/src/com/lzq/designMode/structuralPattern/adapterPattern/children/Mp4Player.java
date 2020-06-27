package com.lzq.designMode.structuralPattern.adapterPattern.children;

import com.lzq.designMode.structuralPattern.adapterPattern.father.AdvancedMediaPlayer;

/**
 * @Author laizhiqiang
 * @Description:创建实现了 AdvancedMediaPlayer 接口的实体类
 * @Date 2020/6/12 0012 16:33
 */
public class Mp4Player implements AdvancedMediaPlayer {
    @Override
    public void playVlc(String fileName) {
        //什么也不做
    }

    @Override
    public void playMp4(String fileName) {
        System.out.println("playMp4:"+fileName);
    }
}
