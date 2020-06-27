package com.lzq.thread.threadCommunication;

import java.util.concurrent.Semaphore;

/**
 * @Author laizhiqiang
 * @Description:线程通讯，Semaphore实现 工厂使用机器工作：
 * 8个工人使用3台机器，机器为互斥资源（每台机器每次只能允许一个人使用）
 * @Date 2020/6/22 0022 21:24
 */
public class SemaphoreThread {
    static class Worker implements Runnable {
        private int workerNum;//工人工号
        private Semaphore semaphore;//机器数

        public Worker(int workerNum, Semaphore semaphore) {
            this.workerNum = workerNum;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                semaphore.acquire();
                //获取工人工号
                String name = Thread.currentThread().getName();
                //工人开始工作
                System.out.println(name + "开始工作");
                Thread.sleep(1000);
                //工人工作完成
                System.out.println(name + "工作完成");
                //释放资源
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Semaphore semaphore=new Semaphore(3);
        for (int i=0;i<8;i++){
            new Thread(new Worker(i,semaphore)).start();
        }
    }
}
