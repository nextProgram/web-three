package com.example.webthree.concurrency.example.atomic;

import com.example.webthree.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author lhx
 * @date 2019/8/28
 * 线程安全性-原子性-atomic
 * AtomicLong
 */
//如果不想每次都写private  final Logger logger = LoggerFactory.getLogger(当前类名.class); 可以用注解@Slf4j;
    //要先安装lombook插件再引入lombook依赖
@Slf4j
@ThreadSafe
public class AtomicLongNote {
    //请求总数
    public static int clientTotal = 5000;

    //同时并发执行的线程数
    public static int threadTotal = 200;

    public static AtomicLong count = new AtomicLong(0);

    public static void main(String[] args) throws InterruptedException {
        //线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        //信号量
        final Semaphore semaphore = new Semaphore(threadTotal);
        //计数器闭锁
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    //当semaphore达到一定并发数，add()会被阻塞
                    //semaphore.acquire()有返回值，add()才会执行
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.error("exception",e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("count:{}",count.get());
    }

    /**
     * 线程安全的写法
     */
    private static void add(){
        //此方法的核心是Unsafe.compareAndSwapLong(简称CAS),获取底层值（主内存中）和当前值（工作内存中），并比较两者
        //当两者相同时，进行加1操作，再覆盖底层的值
        //当两者不同时，重新取当前值的最新值，再判断与底层值是否相同，相同则加一，不同再重新取再判断,从而保证该对象的线程安全
        count.incrementAndGet();
    }
}
