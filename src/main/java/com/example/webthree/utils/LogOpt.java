package com.example.webthree.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 业务操作类，方法上添加
 * @author lhx
 * @date 2019/9/12
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface LogOpt {
    /**
     * @return 数据值
     */
    String value();
}
