package com.lzq.thread.threadCreate;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Author laizhiqiang
 * @Description:线程之实现callable接口
 * @Date 2020/6/11
 */
public class CallableThread {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable callable = new MyCallableThread();
        FutureTask futureTask=new FutureTask(callable);
        new Thread(futureTask).start();
        System.out.println(futureTask.get());
    }

}

class MyCallableThread implements Callable{
    @Override
    public Object call() throws Exception {
        System.out.println("线程启动");
        return "线程启动";
    }
}