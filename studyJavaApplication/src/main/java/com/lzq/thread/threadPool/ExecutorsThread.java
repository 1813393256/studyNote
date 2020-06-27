package com.lzq.thread.threadPool;

import java.util.concurrent.*;

/**
 * @Author laizhiqiang
 * @Description:线程池的创建
 * @Date 2020/6/17 0017 21:13
 */
public class ExecutorsThread {
    public static void main(String[] args) {
        //Executors创建线程池的工具类
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<10;i++){
                    System.out.println(Thread.currentThread().getName()+"运行："+i);
                }
            }
        });
        executorService.shutdown();
    }
}
