package com.example.webthree.concurrency.example.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author lhx
 * @date 2019/8/30
 */
@Slf4j
public class SyschronizedExample1 {
    /**
     * 修饰代码块
     * @param j
     */
    public void test1(int j) {
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                log.info("test1:{}-{}", j, i);
            }
        }
    }

    /**
     * 修饰方法
     * @param j
     */
    public synchronized void test2(int j){
        for (int i = 0; i < 10; i++) {
            log.info("test2:{}-{}", j, i);
        }
    }

    /**
     * 修饰代码块测试
     * @param args
     * 修饰代码块作用于当前对象，不同对象之间互不影响
     * 线程间启动先后由CPU自己决定
     */
    /*public static void main(String[] args) {
        SyschronizedExample1 example1 =  new SyschronizedExample1();
        SyschronizedExample1 example2 = new SyschronizedExample1();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(()->{
            example1.test1(1);
        });
        executorService.execute(()->{
            example2.test1(2);
        });
    }*/

    /**
     * 修饰方法测试
     * @param args
     * 修饰方法作用于当前对象，不同对象之间互不影响
     */
    public static void main(String[] args) {
        SyschronizedExample1 example1 =  new SyschronizedExample1();
        SyschronizedExample1 example2 = new SyschronizedExample1();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(()->{
            example1.test2(1);
        });
        executorService.execute(()->{
            example2.test2(2);
        });
    }
}
