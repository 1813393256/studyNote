package com.lzq.designMode.creationalPattern.singletonPattern.registrationType;

/**
 * @Author laizhiqiang
 * @Description:单例模式，登记式/静态内部类
 * @Date 2020/6/12 0012 13:40
 */
public class RegistrationTypeSingleton {
    private static class SingletonHolder{
        private static final RegistrationTypeSingleton INSTANCE=new RegistrationTypeSingleton();
    }
    private RegistrationTypeSingleton(){}

    public static final RegistrationTypeSingleton getInstance(){
        return SingletonHolder.INSTANCE;
    }
}
