package com.vicky.demo_s.util.thread;

import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @className ThreadPoolExample
 * @desc 定义一个线程池（最低5个、最大10个、线程等待时间1s、队列容量100）
 * 线程池每次会同时执行 5 个任务，这 5 个任务执行完之后，剩余的 5 个任务才会被执行
 * @author Vic
 * @version 1.0
 * @date 2020/10/9 10:32 下午
 **/
public class ThreadPoolExample {

        private static final int CORE_POOL_SIZE = 5;
        private static final int MAX_POOL_SIZE = 10;
        private static final int QUEUE_CAPACITY = 100;
        private static final Long KEEP_ALIVE_TIME = 1L;

        public static void main(String[] args) {

            //使用阿里巴巴推荐的创建线程池的方式
            //通过ThreadPoolExecutor构造函数自定义参数创建
            ThreadPoolExecutor executor = new ThreadPoolExecutor(
                    CORE_POOL_SIZE,
                    MAX_POOL_SIZE,
                    KEEP_ALIVE_TIME,
                    TimeUnit.SECONDS,
                    new ArrayBlockingQueue<>(QUEUE_CAPACITY),
                    new ThreadPoolExecutor.CallerRunsPolicy());
            for(int i=0;i<10;i++){
                MyRunnable myRunnable = new MyRunnable(i + "");
                executor.execute(myRunnable);
            }
            //终止线程池
            executor.shutdown();
            while (!executor.isTerminated()){
            }
            System.out.println("Finished all threads");
        }
}

/**
 * @className MyRunnable
 * @desc 自定义线程 -执行完需要5s左右
 * @author Vic
 * @version 1.0
 * @date 2020/10/9 10:25 下午
 **/
class MyRunnable implements Runnable{
    private String command;

    public MyRunnable(String command){
        this.command = command;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " Start. Time = " + new Date());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " End. Time = " + new Date());
    }

    @Override
    public String toString() {
        return this.command;
    }
}
