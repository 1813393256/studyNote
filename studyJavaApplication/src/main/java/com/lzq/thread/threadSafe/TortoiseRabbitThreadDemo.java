package com.lzq.thread.threadSafe;

/**
 * @Author laizhiqiang
 * @Description:龟兔赛跑线程问题 实现龟兔赛跑
 * 提示：可以采用Math.random()取得0~1之间的随机数模拟比赛进程，
 * 如总距离为100米，在随机数0~0.3之间代表兔子跑，
 * 每次跑2米，在0.3~1之间代表乌龟跑，每次跑1米，
 * 先跑完100米者为胜利者
 * <p>
 * Race类：产生RabbitAndTurtle的两个实例，分别代表兔子和乌龟
 * RabbitAndTurtle类：继承Thread类，实现赛跑的逻辑实现龟兔赛跑
 * 提示：可以采用Math.random()取得0~1之间的随机数模拟比赛进程，
 * 如总距离为100米，在随机数0~0.3之间代表兔子跑，
 * 每次跑2米，在0.3~1之间代表乌龟跑，每次跑1米，
 * 先跑完100米者为胜利者
 * <p>
 * Race类：产生RabbitAndTurtle的两个实例，分别代表兔子和乌龟
 * RabbitAndTurtle类：继承Thread类，实现赛跑的逻辑
 * @Date 2020/6/18 0018 16:21
 */
public class TortoiseRabbitThreadDemo {
    static class TutRat extends Thread {
        private static boolean flag = true;
        private String name;
        public TutRat(String name) {
            this.name = name;
        }
        @Override
        public void run() {
            int distance = 100;
            int pos = 0;
            while (flag) {
                double randomNum = Math.random();
                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (randomNum > 0.3 && name.equals("乌龟") && pos <= distance) {
                    pos += 1;
                    if (pos >= distance) {
                        flag = false;
                        System.out.println(name + "赢得了这场世纪之战");
                    } else {
                        System.out.println(name + ":" + pos);
                    }
                } else if (randomNum <= 0.3 && name.equals("兔子") && pos <= distance) {
                    pos += 2;
                    if (pos >= distance) {
                        flag = false;
                        System.out.println(name + "赢得了这场世纪之战");
                    } else {
                        System.out.println(name + ":" + pos);
                    }
                }

            }
        }
    }
    public static void main(String[] args) {
        TutRat t = new TutRat("乌龟");
        TutRat t1 = new TutRat("兔子");
        t.start();
        t1.start();
    }
}

