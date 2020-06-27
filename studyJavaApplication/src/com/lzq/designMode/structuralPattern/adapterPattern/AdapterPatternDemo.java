package com.lzq.designMode.structuralPattern.adapterPattern;

/**
 * @Author laizhiqiang
 * @Description:适配器模式，测试
 * @Date 2020/6/12 0012 17:04
 */
public class AdapterPatternDemo {
    public static void main(String...args){
        AudioPlayer audioPlayer=new AudioPlayer();
        audioPlayer.play("mp3","xxx.mp3");
        audioPlayer.play("mp4","xxx.mp4");
        audioPlayer.play("vlc","xxx.vlc");
        audioPlayer.play("avi","xxx.avi");

    }
}
