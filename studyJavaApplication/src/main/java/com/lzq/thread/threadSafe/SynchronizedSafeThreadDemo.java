package com.lzq.thread.threadSafe;

/**
 * @Author laizhiqiang
 * @Description:多个线程卖票问题解决，通过Synchronized同步代码块
 * @Date 2020/6/18 0018 16:01
 */
public class SynchronizedSafeThreadDemo {
    //通过锁对象的方式保证线程安全
    static class SecurityThread implements Runnable{
        private Object obj=new Object();
        int ticket=100;
        @Override
        public void run() {
            while (ticket>0){
                synchronized (obj){
                    System.out.println(Thread.currentThread().getName()+":"+ticket--);
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //通过同步方法的方式解决线程安全问题
    static class FuncSafeThread implements Runnable{
        private int ticket=100;
        @Override
        public void run() {
            method();
        }
        public synchronized void method(){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while (ticket>0){
                System.out.println(Thread.currentThread().getName()+":"+ticket--);
            }
        }
    }



    public static void main(String[] args) {
        //同步代码块形式
       /* SecurityThread myThread = new SecurityThread();
        new Thread(myThread).start();
        new Thread(myThread).start();
        new Thread(myThread).start();*/

       //同步方法形式
        FuncSafeThread funcSafeThread = new FuncSafeThread();
        new Thread(funcSafeThread).start();
        new Thread(funcSafeThread).start();
    }
}
