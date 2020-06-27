package com.lzq.designMode.creationalPattern.singletonPattern.dcl;

/**
 * @Author laizhiqiang
 * @Description:双检锁/双重校验锁（DCL即double-checked locking）
 * @Date 2020/6/12 0012 11:45
 */
public class DclSingleton {
    //volatile防止指令重排
    private volatile static DclSingleton instance;
    private DclSingleton(){}
    public static DclSingleton getInstance(){
        if (instance==null){
            synchronized (DclSingleton.class){
                if (instance==null){
                    instance=new DclSingleton();
                }
            }
        }
        return instance;
    }
}
