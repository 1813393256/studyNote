package com.lzq.designMode.structuralPattern.adapterPattern;

import com.lzq.designMode.structuralPattern.adapterPattern.adapter.MediaAdapter;
import com.lzq.designMode.structuralPattern.adapterPattern.father.MediaPlayer;

/**
 * @Author laizhiqiang
 * @Description:适配器模式，创建实现了 MediaPlayer 接口的实体类
 * @Date 2020/6/12 0012 16:51
 */
public class AudioPlayer implements MediaPlayer {
    MediaAdapter mediaAdapter;
    @Override
    public void play(String audioType, String fileName) {
        //播放mp3音乐文件的内置支持
        if (audioType.equalsIgnoreCase("mp3")){
            System.out.println("mp3:"+fileName);
        }
        //mediaAdapter 提供了播放其他文件格式的支持
        else if (audioType.equalsIgnoreCase("vlc")||audioType.equalsIgnoreCase("mp4")){
            mediaAdapter=new MediaAdapter(audioType);
            mediaAdapter.play(audioType,fileName);
        }
        else {
            System.out.println("不兼容媒体格式："+audioType);
        }
    }
}
