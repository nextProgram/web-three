package com.example.webthree.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 消息码注解,类上添加
 *
 * @author lhx
 * @date 2019/9/12
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MsgCode {
    /**
     * @return 数据值
     */
    String value();
}
