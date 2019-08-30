package com.example.webthree.concurrency.example.atomic;

import com.example.webthree.concurrency.annotations.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @author lhx
 * @date 2019/8/28
 * 线程安全性-原子性-atomic
 * AtomicIntegerFieldUpdater 更新某一个类的某个字段的值，并且该字段必须用volatile修饰以及不能使用static
 *
 */
//如果不想每次都写private  final Logger logger = LoggerFactory.getLogger(当前类名.class); 可以用注解@Slf4j;
//要先安装lombook插件再引入lombook依赖
@Slf4j
@ThreadSafe
public class AtomicIntegerFieldUpdaterNote {

    private static AtomicIntegerFieldUpdater<AtomicIntegerFieldUpdaterNote> updater = AtomicIntegerFieldUpdater.newUpdater(AtomicIntegerFieldUpdaterNote.class, "count");
    @Getter
    private volatile int count = 100;
    private static AtomicIntegerFieldUpdaterNote atomicIntegerFieldUpdaterNote = new AtomicIntegerFieldUpdaterNote();

    public static void main(String[] args) throws InterruptedException {
        if (updater.compareAndSet(atomicIntegerFieldUpdaterNote, 100, 200)) {
            log.info("update success to 200,[{}]", atomicIntegerFieldUpdaterNote.getCount());
        }
        if (updater.compareAndSet(atomicIntegerFieldUpdaterNote, 100, 300)) {
            log.info("update success to 300,[{}]", atomicIntegerFieldUpdaterNote.getCount());
        } else {
            log.info("update failure,[{}]", atomicIntegerFieldUpdaterNote.getCount());
        }
    }
}
