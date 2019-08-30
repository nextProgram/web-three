package com.example.webthree.concurrency.example.atomic;

import com.example.webthree.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author lhx
 * @date 2019/8/28
 * 线程安全性-原子性-atomic
 * AtomicReference
 * 补充：AtomicStampedReference解决了CAS的ABA问题，有个版本的标志，每次更新值同时更新值的版本，从而解决ABA问题
 */
//如果不想每次都写private  final Logger logger = LoggerFactory.getLogger(当前类名.class); 可以用注解@Slf4j;
    //要先安装lombook插件再引入lombook依赖
@Slf4j
@ThreadSafe
public class AtomicReferenceNote {

    private static AtomicReference<Integer> count = new AtomicReference<>(0);

    public static void main(String[] args) throws InterruptedException {
        count.compareAndSet(0, 2);
        count.compareAndSet(0, 1);
        count.compareAndSet(1, 3);
        count.compareAndSet(2, 4);
        count.compareAndSet(3, 5);
        log.info("count:{}",count.get());
    }
}
