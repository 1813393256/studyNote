package com.lzq.thread.threadCommunication;

/**
 * @Author laizhiqiang
 * @Description:线程通讯
 * 线程休眠唤醒，Object的wait，notify,notifyAll方法 多线程打印10以内的奇偶数；
 * 从0开始，当是奇数时，奇数线程打印，偶数线程等待；
 * 当是偶数时，偶数线程打印，奇数线程等待；
 * @Date 2020/6/19 0019 17:50
 */
public class ObjectThread {
    private int num = 0;//要打印的数
    private Object obj=new Object();//锁
    //奇数线程方法，由奇数线程调用
    public void odd() {
        while (num < 10) {
            synchronized (obj) {
                if (num % 2 == 1) {
                    System.out.println("奇数：" + num++);
                    obj.notify();
                } else {
                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    //偶数线程方法，由偶数线程调用
    public void event() {
        while (num < 10) {
            synchronized (obj) {
                if (num % 2 == 0) {
                    System.out.println("偶数：" + num++);
                    obj.notify();
                } else {
                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        ObjectThread objectThread = new ObjectThread();
        //奇数线程启动
        new Thread(new Runnable() {
            @Override
            public void run() {
                objectThread.odd();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                objectThread.event();
            }
        }).start();
    }
}
