package com.lzq.designMode.creationalPattern.singletonPattern.lazy;

/**
 * @Author laizhiqiang
 * @Description:懒汉模式，测试
 * @Date 2020/6/12 0012 11:26
 */
public class LazySingletonDemo {
    public static void main(String...args){
        LazySingleton instance = LazySingleton.getInstance();
    }
}
