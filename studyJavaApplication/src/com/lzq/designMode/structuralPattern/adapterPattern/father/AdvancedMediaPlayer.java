package com.lzq.designMode.structuralPattern.adapterPattern.father;

/**
 * @Author laizhiqiang
 * @Description:适配器模式，为更高级的媒体播放器创建接口
 * @Date 2020/6/12 0012 16:30
 */
public interface AdvancedMediaPlayer {
    public void playVlc(String fileName);
    public void playMp4(String fileName);
}
