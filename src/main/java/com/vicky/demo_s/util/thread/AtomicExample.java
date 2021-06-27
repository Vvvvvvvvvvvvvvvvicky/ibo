package com.vicky.demo_s.util.thread;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicMarkableReference;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomicExample {

    private static Log log = LogFactory.getLog(AtomicExample.class);

    /**问题一：
     *     1、在多线程的环境中，线程a从共享的地址X中读取到了对象A。
     *     2、在线程a准备对地址X进行更新之前，线程b将地址X中的值修改为了B。
     *     3、接着线程b将地址X中的值又修改回了A。
     *     4、最新线程a对地址X执行CAS，发现X中存储的还是对象A，对象匹配，CAS成功。
     *
     *     这种情况对于java来说不会有问题
     *     因为在java中，只要两个对象的地址一致，就表示这两个对象是相等的。
     *     23操作不会对结果有影响
     */
    public static void example() {
        CustUser a = new CustUser();
        CustUser b = new CustUser();
        CustUser c = new CustUser();

        //AtomicReference的CAS确实比较的两者是否为统一对象，对其中内容的变化并不关心
        AtomicReference<CustUser> atomicReference = new AtomicReference<>(a);
        log.info(atomicReference.compareAndSet(a,b));
        log.info(atomicReference.compareAndSet(b,a));
        //对a的属性进行修改，还是可以执行成功(java对象地址一样，就是相等)
        a.setName("new name");
        log.info(atomicReference.compareAndSet(a,c));
    }


    /**
     * ABA问题复现(多线程版) Java中内存地址一样就是同个对象（修改对象的属性->ABA问题仍会把修改后的对象当做是同一个）
     * t1 结果：true CustUser{name='BBB'}
     * t1 结果：true CustUser{name='CCC'}
     * t2 结果：true CustUser{name='BBB'}
     */
    public static void example3() {
        CustUser a = new CustUser("AAA");
        CustUser b = new CustUser("BBB");
        AtomicReference<CustUser> atomicReference = new AtomicReference<CustUser>(a);

        //线程1：A->B->A'
        new Thread(() -> {
            boolean result1 = atomicReference.compareAndSet(a, b);
            System.out.println(Thread.currentThread().getName() + " 结果：" + result1 + " " + atomicReference.get());

            //变量a的属性变更了，a->a'
            a.setName("CCC");
            boolean result2 = atomicReference.compareAndSet(b, a);
            System.out.println(Thread.currentThread().getName() + " 结果：" + result2 + " " + atomicReference.get());
        }, "t1").start();

        //线程2:A'->A(这里改A’，还是使用的A，因为Java中内存地址一样就是同个对象，没有识别出a已经被改过一次了)
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " 结果：" + atomicReference.compareAndSet(a, b) + " " + atomicReference.get());
        }, "t2").start();
        //全部线程结束后，A->A
    }


    /**
     * 解决-使用带版本号的AtomicStampedReference
     * t2 当前版本号：1
     * t1 初始版本号：1
     * t1 第1次更新结果:true 版本号：2
     * t1 第2次更新结果:true 版本号：3
     * t2 修改是否成功： false 当前版本 ：3
     * t2 当前实际值： 100
     */
    public static void example2() {
        AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<Integer>(100,1);

        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName()+" 初始版本号："+atomicStampedReference.getStamp());
            boolean result1 = atomicStampedReference.compareAndSet(100, 101,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName()+" 第1次更新结果:"+result1+" 版本号："+atomicStampedReference.getStamp());
            boolean result2 = atomicStampedReference.compareAndSet(101, 100,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName()+" 第2次更新结果:"+result2+" 版本号："+atomicStampedReference.getStamp());
        }, "t1").start();

        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName()+" 当前版本号："+atomicStampedReference.getStamp());
            boolean result = atomicStampedReference.compareAndSet(100, 2020,stamp,stamp+1);
            try {
                TimeUnit.NANOSECONDS.sleep(99);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " 修改是否成功： "+ result+" 当前版本 ：" + atomicStampedReference.getStamp());
            System.out.println(Thread.currentThread().getName() + " 当前实际值： " + atomicStampedReference.getReference());

        }, "t2").start();
    }

    /**
     * 多线程版解决-使用带版本号的AtomicStampedReference
     */
    public static void example4() {
        CustUser a = new CustUser();
        CustUser b = new CustUser();
        CustUser c = new CustUser();

        //使用带版本号的AtomicStampedReference
        AtomicStampedReference<CustUser> atomicStampedReference = new AtomicStampedReference<CustUser>(a,0);
        log.info(atomicStampedReference.compareAndSet(a,b,0,1));
        log.info(atomicStampedReference.compareAndSet(b,a,1,2));
        //对a的属性进行修改，但是因为a的版本不正确，所以执行失败
        a.setName("new name");
        log.info(atomicStampedReference.compareAndSet(a,c,0,1));
    }

    public static void atomicMarkableReferenceExample(boolean[] markHolder){
            markHolder[0] = true;
    }

    public static void main(String[] args) {
//        example();
//        example2();
//            example3();
//        example4();
        boolean[] array = new boolean[]{false,true};
        atomicMarkableReferenceExample(array);
        System.out.println(array[0]);
    }
}

class CustUser{
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CustUser(String name) {
        this.name = name;
    }

    public CustUser(){}

    @Override
    public String toString() {
        return "CustUser{" +
                "name='" + name + '\'' +
                '}';
    }
}