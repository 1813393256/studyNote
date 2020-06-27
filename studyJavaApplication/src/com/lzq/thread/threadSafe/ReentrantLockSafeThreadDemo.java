package com.lzq.thread.threadSafe;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author laizhiqiang
 * @Description:重入锁解决线程安全问题
 * @Date 2020/6/19 0019 9:53
 */
public class ReentrantLockSafeThreadDemo {
    static class ReenLock implements Runnable {
        private int ticket = 100;
        Lock lock = new ReentrantLock(true);//true参数表示是公平的，多个线程都公平的拥有执行权，false表示非公平的，相当于独占锁

        @Override
        public void run() {
            while (true) {
                lock.lock();
                try {
                    if (ticket > 0) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(Thread.currentThread().getName() + ":" + ticket--);
                    }
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    public static void main(String[] args) {
        ReenLock reenLock = new ReenLock();
        new Thread(reenLock).start();
        new Thread(reenLock).start();
    }
}
