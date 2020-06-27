package com.lzq.thread.threadCreate;

/**
 * @Author laizhiqiang
 * @Description:多线程交替打印
 * @Date 2020/6/11
 */
public class ManyThreadPrint {
    public static void main(String[] args) {
        Object o1 = new Object();
        Object o2 = new Object();
        Object o3 = new Object();
        Thread thread = new Thread(new MyManyThreadPrint("a", o1, o2));
        Thread thread1 = new Thread(new MyManyThreadPrint("b", o2, o3));
        Thread thread2 = new Thread(new MyManyThreadPrint("c", o3, o1));
        thread.start();
        thread1.start();
        thread2.start();
    }
}

class MyManyThreadPrint implements Runnable {
    private Object pre;
    private Object cur;
    private String name;

    public MyManyThreadPrint(String name, Object pre, Object cur) {
        this.name = name;
        this.pre = pre;
        this.cur = cur;
    }

    @Override
    public void run() {
        int i = 0;
        while (i < 10) {
            synchronized (pre) {
                synchronized (cur) {
                    System.out.println(Thread.currentThread().getName() + ":" + name);
                    i++;
                    cur.notify();
                }
                try {
                    if (i == 10) {
                        pre.notifyAll();
                    } else {
                        pre.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
