package com.lzq.thread.threadCommunication;

import java.util.concurrent.CountDownLatch;

/**
 * @Author laizhiqiang
 * @Description:线程通讯，CountDownLatch，等待其他线程执行完成，自身线程才执行
 * 教练等待多个运动员准备完成，然后才开始训练
 * @Date 2020/6/21 0021 10:26
 */
public class CountDownLatchThread {
    private CountDownLatch countDownLatch=new CountDownLatch(3);//参数3,表示要等待3个线程执行完毕，在调用自身线程

    /**
     * 运动员方法
     */
    public void athlete(){
        //获取线程名
        String name = Thread.currentThread().getName();
        //运动员准备中
        System.out.println(name+"运动员准备中...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //运动员准备完成
        System.out.println(name+"运动员准备完成");
        countDownLatch.countDown();
    }

    /**
     * 教练方法
     */
    public void coach(){
        //获取线程名
        String name = Thread.currentThread().getName();
        //教练准备
        System.out.println(name+"准备中...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //教练等待所有运动员准备完成
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //开始训练
        System.out.println("所有运动员已准备完成，"+name+"开始训练！");
    }

    public static void main(String[] args) {
        //创建CountDownLatchThread实例
        CountDownLatchThread countDownLatchThread=new CountDownLatchThread();
        //线程模拟教练准备
        new Thread(new Runnable() {
            @Override
            public void run() {
                countDownLatchThread.coach();
            }
        },"教练").start();

        //线程模拟3个运动员准备
        new Thread(new Runnable() {
            @Override
            public void run() {
                countDownLatchThread.athlete();
            }
        },"运动员1").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                countDownLatchThread.athlete();
            }
        },"运动员2").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                countDownLatchThread.athlete();
            }
        },"运动员3").start();

    }
}
