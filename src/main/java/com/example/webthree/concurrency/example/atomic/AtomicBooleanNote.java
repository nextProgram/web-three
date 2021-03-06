package com.example.webthree.concurrency.example.atomic;

import com.example.webthree.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author lhx
 * @date 2019/8/28
 * 线程安全性-原子性-atomic
 * AtomicBoolean
 */
//如果不想每次都写private  final Logger logger = LoggerFactory.getLogger(当前类名.class); 可以用注解@Slf4j;
//要先安装lombook插件再引入lombook依赖
@Slf4j
@ThreadSafe
public class AtomicBooleanNote {
    private static AtomicBoolean isHappend = new AtomicBoolean(false);
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
                    test();
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.error("exception", e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("isHappend:{}", isHappend.get());
    }

    /**
     * 线程安全的写法
     */
    private static void test() {
        //5000次中只执行了一次
        //可实际运用于只执行一次的代码
        if (isHappend.compareAndSet(false, true)) {
            log.info("excute");
        }
    }
}
