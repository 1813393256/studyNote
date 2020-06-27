package com.lzq.designMode.creationalPattern.singletonPattern.hungry;

/**
 * @Author laizhiqiang
 * @Description:单例模式，饿汉式
 * @Date 2020/6/12 0012 11:43
 */
public class HungrySingleton {
    private static HungrySingleton instance=new HungrySingleton();
    private HungrySingleton(){}
    public static HungrySingleton getInstance(){
        return instance;
    }
}
