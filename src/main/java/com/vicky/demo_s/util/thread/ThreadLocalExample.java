package com.vicky.demo_s.util.thread;

import java.text.SimpleDateFormat;
import java.util.Random;

/**
 * ThreadLocal示例
 * ThreadLocal 主要解决的就是让每个线程绑定自己的值，
 * 可以将ThreadLocal类形象的比喻成存放数据的盒子，
 * 盒子中可以存储每个线程的私有数据。
 *
 * 如果创建了一个ThreadLocal变量，
 * 那么访问这个变量的每个线程都会有这个变量的本地副本
 * 使用 get（） 和 set（） 方法来获取默认值或将其值更改为当前线程所存的副本的值
 *
 * 原理：
 * Thread 类中有一个 threadLocals 和 一个 inheritableThreadLocals 变量，
 * 它们都是 ThreadLocalMap 类型的变量,可以把 ThreadLocalMap 理解为ThreadLocal 类实现的定制化的 HashMap。
 * 默认情况下这两个变量都是null，只有当前线程调用 ThreadLocal 类的 set或get方法时才创建它们，实际上调用这两个方法的时候，线程调用的是ThreadLocalMap类对应的 get()、set()方法。
 *
 */
public class ThreadLocalExample implements Runnable{
    // SimpleDateFormat 不是线程安全的，每个线程要有自己的独立副本
    private static final ThreadLocal<SimpleDateFormat> formatter = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyyMMdd HHmm"));

    public static void main(String[] args) throws InterruptedException {
        ThreadLocalExample example = new ThreadLocalExample();

        for(int i = 0;i<10; i++){
            Thread t =new Thread(example,i+"");
            Thread.sleep(new Random().nextInt(1000));
            t.start();
        }
    }
    @Override
    public void run() {
        System.out.println("Thread Name = "+Thread.currentThread().getName() + " default Formatter = "+ formatter.get().toPattern());
        try {
            Thread.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //formatter变更了，但是不会影响其他线程
        formatter.set(new SimpleDateFormat());

        System.out.println("Thread Name = "+Thread.currentThread().getName() + " Formatter = "+ formatter.get().toPattern());
    }
}
