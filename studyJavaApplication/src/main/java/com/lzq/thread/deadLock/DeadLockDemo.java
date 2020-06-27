package com.lzq.thread.deadLock;

/**
 * @Author laizhiqiang
 * @Description:死锁代码演示，演示两个线程抢占资源造成死锁问题
 * @Date 2020/6/19 0019 13:43
 */
public class DeadLockDemo {

    static class Dead implements Runnable{
        private boolean flag;//决定线程走向的标记
        private static Object obj1=new Object();//锁对象1，static是为了让线程共享资源
        private static Object obj2=new Object();//锁对象2
        public Dead(boolean flag) {
            this.flag = flag;
        }
        @Override
        public void run() {
            if (flag){
               synchronized (obj1){
                   System.out.println(Thread.currentThread().getName()+"已获取到资源obj1，请求资源obj2");
                   try {
                       Thread.sleep(1000);
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
                   synchronized (obj2){
                       System.out.println(Thread.currentThread().getName()+"已获取到资源obj1和obj2");
                   }
               }
            }else {
                synchronized (obj2){
                    System.out.println(Thread.currentThread().getName()+"已获取到资源obj2，请求资源obj1");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (obj1){
                        System.out.println(Thread.currentThread().getName()+"已获取到资源obj1和obj2");
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
       new Thread(new Dead(true)).start();
       new Thread(new Dead(false)).start();
    }
}
