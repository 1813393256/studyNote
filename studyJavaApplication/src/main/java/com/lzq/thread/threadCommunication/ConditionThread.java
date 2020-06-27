package com.lzq.thread.threadCommunication;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author laizhiqiang
 * @Description:线程通讯 线程休眠唤醒，Condition的await，singnal,singnalAll方法 多线程打印10以内的奇偶数；
 * 从0开始，当是奇数时，奇数线程打印，偶数线程等待；
 * 当是偶数时，偶数线程打印，奇数线程等待；
 * @Date 2020/6/21 0021 9:51
 */
public class ConditionThread {
    private int num = 0;//要打印的数
    private Lock lock = new ReentrantLock();//参数false，独占锁，用于创建Condition对象
    private Condition condition = lock.newCondition();

    /**
     * 奇数打印方法
     */
    public void odd() {
        while (num < 10) {
            lock.lock();
            try {
                if (num % 2 == 1) {
                    System.out.println("奇数:" + num++);
                    condition.signal();
                } else {
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } finally {
                lock.unlock();
            }

        }
    }

    /**
     * 偶数打印方法
     */
    public void event() {
        while (num < 10) {
            lock.lock();
            try {
                if (num % 2 == 0) {
                    System.out.println("偶数:" + num++);
                    condition.signal();
                } else {
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } finally {
                lock.unlock();
            }

        }
    }

    /**
     * 主函数入口
     *
     * @param args
     */
    public static void main(String[] args) {
        ConditionThread conditionThread = new ConditionThread();
        //奇数打印线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                conditionThread.odd();
            }
        }).start();
        //偶数线程打印
        new Thread(new Runnable() {
            @Override
            public void run() {
                conditionThread.event();
            }
        }).start();
    }

}
