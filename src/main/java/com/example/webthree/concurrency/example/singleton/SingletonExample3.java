package com.example.webthree.concurrency.example.singleton;

import com.example.webthree.concurrency.annotations.NotThreadSafe;

/**
 * @author lhx
 * @date 2019/9/3
 * 单例模式-双重同步锁单例模式
 * 单例实例在第一次使用时创建
 */
@NotThreadSafe
public class SingletonExample3 {
    //私有构造函数
    private SingletonExample3() {
    }
    //单例对象
    private static SingletonExample3 instance = null;
    //静态工厂方法获取单例对象
    public static SingletonExample3 getInstance(){
        //如此写法线程不安全
        if (instance == null) {//双重检测机制
            synchronized (SingletonExample3.class){//同步锁
                if (instance == null) {
                    instance = new SingletonExample3();
                }
            }
        }
        return instance;
    }
}
