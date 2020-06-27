package com.lzq.designMode.creationalPattern.singletonPattern.enumeration;

/**
 * @Author laizhiqiang
 * @Description:单例模式，枚举
 * @Date 2020/6/12 0012 13:49
 */
public class EnumSingleton {
    private EnumSingleton(){}
    static enum SingletonEnum{
        INSTANCE;
        private EnumSingleton enumSingleton;
        private SingletonEnum(){
            enumSingleton=new EnumSingleton();
        }
        public EnumSingleton getInstance(){
            return enumSingleton;
        }
    }
    public static EnumSingleton getInstance(){
        return SingletonEnum.INSTANCE.getInstance();
    }
}
