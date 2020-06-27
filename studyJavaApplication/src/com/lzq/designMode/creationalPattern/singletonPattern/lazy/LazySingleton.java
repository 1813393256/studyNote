package com.lzq.designMode.creationalPattern.singletonPattern.lazy;

/**
 * @Author laizhiqiang
 * @Description:单例模式，懒汉式，多线程安全
 * @Date 2020/6/12 0012 11:22
 */
public class LazySingleton {
    private static LazySingleton instance;
    private LazySingleton(){
    }

    public static synchronized LazySingleton getInstance(){
        if (instance==null){
            instance=new LazySingleton();
        }
        return instance;
    }
}
