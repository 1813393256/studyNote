package com.lzq.thread.threadCommunication;

import java.util.Date;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Author laizhiqiang
 * @Description:线程通讯,使用CyclicBarrier方式，等待一组线程准备完毕，然后同时执行
 * @Date 2020/6/21 0021 10:52
 */
public class CyclicBarrierThread {
    //创建CyclicBarrier实例
    private CyclicBarrier cyclicBarrier=new CyclicBarrier(3);//参数3表示等待3个线程准备

    /**
     * 线程准备方法
     */
    public void threadWait(){
        //获取线程名称
        String name = Thread.currentThread().getName();
        //当前线程准备
        System.out.println(name+"线程准备...");
        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        //线程准备完成
        System.out.println(name+"线程准备完成！"+"时间："+new Date().getTime());
    }

    /**
     * 主函数入口
     * @param args
     */
    public static void main(String[] args) {
        //创建CyclicBarrierThread实例
        CyclicBarrierThread cyclicBarrierThread = new CyclicBarrierThread();
        //创建三个线程，并启动
        new Thread(new Runnable() {
            @Override
            public void run() {
                cyclicBarrierThread.threadWait();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                cyclicBarrierThread.threadWait();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                cyclicBarrierThread.threadWait();
            }
        }).start();
    }
}
