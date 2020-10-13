package com.example.webthree.concurrency.example.singleton;

import com.example.webthree.concurrency.annotations.ThreadSafe;

/**
 * @author lhx
 * @date 2019/9/3
 * 单例模式-饿汉模式
 * 单例实例在类装载时创建
 * 优点：线程安全
 * 缺点：1、如果构造函数包含过多的处理，可能造成性能问题。
 * 2、有可能类被加载了，但是最终并没有被使用，造成资源的浪费。
 */
@ThreadSafe
public class SingletonExample2 {
    //私有构造函数
    private SingletonExample2() {
    }
    //单例对象
    private static SingletonExample2 instance = new SingletonExample2();
    //静态工厂方法获取单例对象
    public static SingletonExample2 getInstance(){
        return instance;
    }
}
