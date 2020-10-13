package com.example.webthree.concurrency.example.singleton;

import com.example.webthree.concurrency.annotations.NotThreadSafe;

/**
 * @author lhx
 * @date 2019/9/3
 * 单例模式-懒汉模式
 * 单例实例在第一次使用时创建
 * 线程不安全，多线程情况下可能会new多个实例出来
 */
@NotThreadSafe
public class SingletonExample1 {
    //私有构造函数
    private SingletonExample1() {
    }
    //单例对象
    private static SingletonExample1 instance = null;
    //懒汉模式的改进：在工厂方法上加入synchronized关键字，线程安全，但是不推荐（性能上的考虑）
    // public static synchronized SingletonExample1 getInstance(){}
    //静态工厂方法获取单例对象
    public static SingletonExample1 getInstance(){
        //如此写法线程不安全
        if (instance == null) {
            instance = new SingletonExample1();
        }
        return instance;
    }
}
