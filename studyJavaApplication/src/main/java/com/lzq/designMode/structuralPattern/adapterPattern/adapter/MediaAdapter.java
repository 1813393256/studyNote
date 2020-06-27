package com.lzq.designMode.structuralPattern.adapterPattern.adapter;

import com.lzq.designMode.structuralPattern.adapterPattern.children.Mp4Player;
import com.lzq.designMode.structuralPattern.adapterPattern.children.VlcPlayer;
import com.lzq.designMode.structuralPattern.adapterPattern.father.AdvancedMediaPlayer;
import com.lzq.designMode.structuralPattern.adapterPattern.father.MediaPlayer;

/**
 * @Author laizhiqiang
 * @Description:创建实现了 MediaPlayer 接口的适配器类。
 * @Date 2020/6/12 0012 16:35
 */
public class MediaAdapter implements MediaPlayer {
    AdvancedMediaPlayer advancedMediaPlayer;
    public MediaAdapter(String audioType){
        if (audioType.equalsIgnoreCase("vlc")){
            advancedMediaPlayer=new VlcPlayer();
        }else if (audioType.equalsIgnoreCase("mp4")){
            advancedMediaPlayer=new Mp4Player();
        }
    }

    @Override
    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("vlc")){
           advancedMediaPlayer.playVlc(fileName);
        }else if (audioType.equalsIgnoreCase("mp4")){
            advancedMediaPlayer.playMp4(fileName);
        }
    }
}
