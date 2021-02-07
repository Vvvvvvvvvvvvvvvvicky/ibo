package com.vicky.demo_s.util;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentExample {
    HashMap hashMap;//线程不安全
    Hashtable hashtable;//线程安全（put、remove都是线程独占（sychronized修饰））
    ConcurrentHashMap concurrentHashMap;
}
