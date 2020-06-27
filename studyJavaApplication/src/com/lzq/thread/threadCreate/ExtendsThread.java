package com.lzq.thread.threadCreate;

/**
 * @Author laizhiqiang
 * @Description:线程之继承Thread接口
 * @Date 2020/6/11
 */
public class ExtendsThread extends Thread{

    public static void main(String[] args) {
        new ExtendsThread().start();
    }

    @Override
    public void run() {
       System.out.println(currentThread().getName()+"启动");
    }
}
