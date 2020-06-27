package com.lzq.designMode.structuralPattern.adapterPattern.father;

/**
 * @Author laizhiqiang
 * @Description:适配器模式，为媒体播放器创建接口
 * @Date 2020/6/12 0012 16:29
 */
public interface MediaPlayer {
    public void play(String audioType, String fileName);
}
