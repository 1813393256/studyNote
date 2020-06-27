package com.lzq.thread.threadCreate;

/**
 * @Author laizhiqiang
 * @Description:守护线程测试
 * @Date 2020/6/11 0011
 */
public class DeamonThread {
    public static void main(String[] args) {
        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+"线程启动");
            }
        });
        t1.setDaemon(true);
        t1.start();
        System.out.println("结束");
    }
}
