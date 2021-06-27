package com.vicky.demo_s.util.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

//Runnable是执行工作的独立任务，但是它不返回任何值。在Java SE5中引入的Callable是一种具有类型参数的泛型，它的类型参数表的是从方法call()中返回的值，并且必须使用ExecutorServices.submit()方法调用它，下面是一个简单示例。package com.test;
public class CallableExample {
    public static void main(String[] args) {
        ExecutorService exec=Executors.newCachedThreadPool();
        List<Future<String>> results=new ArrayList<Future<String>>();

        for(int i=0;i<5;i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            results.add(exec.submit(new TaskWithResult(i)));
        }

        for(Future<String> fs :results) {
            try {
                System.out.println(fs.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

}

class TaskWithResult implements Callable<String> {
    private int id;
    public TaskWithResult(int id) {
        this.id=id;
    }

    @Override
    public String call() throws Exception {
        return "result of TaskWithResult "+id;
    }
}
