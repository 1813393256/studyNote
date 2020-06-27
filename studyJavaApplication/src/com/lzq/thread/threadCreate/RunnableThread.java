package com.lzq.thread.threadCreate;

/**
 * @Author laizhiqiang
 * @Description:线程之实现Runnable接口
 * @Date 2020/6/11
 */
public class RunnableThread {
    public static void main(String[] args) {
        Thread thread=new Thread(new MyRunnableThread());
        thread.start();
    }
}

class MyRunnableThread implements Runnable{
    @Override
    public void run() {
        System.out.println("runnable启动");
    }
}
