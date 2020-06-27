package com.lzq.thread.threadCreate;

/**
 * @Author laizhiqiang
 * @Description:两个线程之交替打印
 * @Date 2020/6/11
 */
public class TwoThreadPrint {
    public static void main(String[] args) {
        MyTwoThreadPrint myTwoThreadPrint = new MyTwoThreadPrint();
        Thread thread = new Thread(myTwoThreadPrint);
        Thread thread1 = new Thread(myTwoThreadPrint);
        thread.start();
        thread1.start();
    }
}

class MyTwoThreadPrint implements Runnable {
    private int i = 0;

    @Override
    public void run() {
        while (i < 10) {
            synchronized (this) {
                this.notify();
                System.out.println(Thread.currentThread().getName() + ":" + i++);
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}