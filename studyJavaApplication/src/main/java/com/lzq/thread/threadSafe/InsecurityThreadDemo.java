package com.lzq.thread.threadSafe;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author laizhiqiang
 * @Description:多个线程卖票问题,会出现贩卖同一张票的情况
 * @Date 2020/6/18 0018 15:07
 */
public class InsecurityThreadDemo {
    static class UnSafeThread implements Runnable {
        int ticket = 100;
        @Override
        public void run() {
            while (ticket > 0) {
                System.out.println(Thread.currentThread().getName() + ":" + ticket--);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        UnSafeThread unSafeThread = new UnSafeThread();
        /*ExecutorService executorService = Executors.newFixedThreadPool(20);
        executorService.execute(myThread);*/
        new Thread(unSafeThread).start();
        new Thread(unSafeThread).start();
        new Thread(unSafeThread).start();
        new Thread(unSafeThread).start();


    }
}
