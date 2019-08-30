package com.example.webthree.concurrency.example.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author lhx
 * @date 2019/8/30
 */
@Slf4j
public class SyschronizedExample2 {
    /**
     * 修饰类
     * @param j
     */
    public static void test1(int j) {
        synchronized (SyschronizedExample2.class) {
            for (int i = 0; i < 10; i++) {
                log.info("test1:{}-{}", j, i);
            }
        }
    }

    /**
     * 修饰静态方法
     * @param j
     */
    public static synchronized void test2(int j){
        for (int i = 0; i < 10; i++) {
            log.info("test2:{}-{}", j, i);
        }
    }

    /**
     * 修饰静态方法测试
     */
    /*public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(()->{
            SyschronizedExample2.test2(1);
        });
        executorService.execute(()->{
            SyschronizedExample2.test2(2);
        });
    }*/
    /**
     * 修饰类测试
     */
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(()->{
            SyschronizedExample2.test1(1);
        });
        executorService.execute(()->{
            SyschronizedExample2.test1(2);
        });
    }
}
