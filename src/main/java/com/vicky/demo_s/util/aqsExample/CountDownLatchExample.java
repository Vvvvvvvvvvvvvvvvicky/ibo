package com.vicky.demo_s.util.aqsExample;

import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchExample {

    private static final int threadCount = 6;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        System.out.println("start");
        for(int i=0;i<threadCount;i++){
            int no = i;
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep((long)(Math.random() * 10000));
                        System.out.println("No." + String.valueOf(no) + " task finished!");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        countDownLatch.countDown(); //单个线程完成后减一
                    }
                }
            };
            threadPool.execute(run);
        }
        countDownLatch.await();
        threadPool.shutdown();
        System.out.println("end");
    }
}

class MyRunnable implements Runnable{
    private int command;

    public MyRunnable(int command){
        this.command = command;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ":" + this.command + " Start. Time = " + new Date());
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ":" + this.command + " End. Time = " + new Date());
    }

    @Override
    public String toString() {
        return String.valueOf(this.command);
    }
}

