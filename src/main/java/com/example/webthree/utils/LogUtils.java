package com.example.webthree.utils;

import lombok.Setter;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * logback实现封装，统一使用面向slf4j接口。提供了静态方法的DUBUG、INFO、WARN、ERROR级别日志调用
 * slf4j提供占位符功能
 *
 * @author lhx
 * @date 2019/9/10
 */
public class LogUtils {
    /* DEBUG级别 */
    public static final String DEBUG = "DEBUG";
    /* INFO级别 */
    public static final String INFO = "INFO";
    /* WARN级别 */
    public static final String WARN = "WARN";
    /* ERROR级别 */
    public static final String ERROR = "ERROR";
    /* 数据库db级别 */
    @Setter
    public static String dbLogLevel = "INFO";

    /**
     * 项目打印日志的方式--正常日志，想要使用系统日志必须符合相应条件
     * 1.所在的类必须加@MsgCode(MsgCodeConst.XXX)注解，XXX为该模块在消息码中的模块名
     * 2.在所要打印日志的方法上加入@LogOpt(OptType.XXX)注解，值为OptType静态值
     * 3.如果1,2都不加，则日志组件使用默认配置
     * @param params 日志消息中的参数
     */
    public static void escDebug(Object... params) {
        //打印日志
        if (isDebugOpen()) {
             //获取消息码
            MessageCode msg = MsgUtils.msg(getMsgCode() + "_S");
            LoggerFactory.getLogger(getLogClass()).debug(msg.getMsg(),params);
        }

    }
    /**
     * 项目打印日志的方式--正常日志，想要使用系统日志必须符合相应条件
     * 1.所在的类必须加@MsgCode(MsgCodeConst.XXX)注解，XXX为该模块在消息码中的模块名
     * 2.在所要打印日志的方法上加入@LogOpt(OptType.XXX)注解，值为OptType静态值
     * 3.如果1,2都不加，则日志组件使用默认配置
     * @param params 日志消息中的参数
     */
    public static void escInfo(Object... params) {
        //打印日志
        if (isInfoOpen()) {
            //获取消息码
            MessageCode msg = MsgUtils.msg(getMsgCode() + "_S");
            LoggerFactory.getLogger(getLogClass()).info(msg.getMsg(),params);
        }

    }

    /**
     * 项目打印日志的方式--正常日志，想要使用系统日志必须符合相应条件
     * 1.所在的类必须加@MsgCode(MsgCodeConst.XXX)注解，XXX为该模块在消息码中的模块名
     * 2.在所要打印日志的方法上加入@LogOpt(OptType.XXX)注解，值为OptType静态值
     * 3.如果1,2都不加，则日志组件使用默认配置
     * @param params 日志消息中的参数
     */
    public static void escError(Object... params) {
        //打印日志
        if (isErrorOpen()) {
            //获取消息码
            MessageCode msg = MsgUtils.msg(getMsgCode() + "_E");
            LoggerFactory.getLogger(getLogClass()).error(msg.getMsg(),params);
        }

    }
    /**
     * 项目打印日志的方式--正常日志，想要使用系统日志必须符合相应条件
     * 1.所在的类必须加@MsgCode(MsgCodeConst.XXX)注解，XXX为该模块在消息码中的模块名
     * 2.在所要打印日志的方法上加入@LogOpt(OptType.XXX)注解，值为OptType静态值
     * 3.如果1,2都不加，则日志组件使用默认配置
     * @param params 日志消息中的参数
     */
    public static void escException(Throwable t,Object... params) {
        //打印日志
        if (isErrorOpen()) {
            //获取消息码
            MessageCode msg = MsgUtils.msg(getMsgCode() + "_E");
            LoggerFactory.getLogger(getLogClass()).error(msg.getMsg(),params,t);
        }

    }

    /**
     * ERROR日志
     * 可带参，可不带参
     *
     * @param message 日志信息
     * @param params  可变参数
     */
    public static void error(String message, Object... params) {
        if (isErrorOpen()) {
            LoggerFactory.getLogger(getLogClass()).error(message, params);
        }
    }

    /**
     * ERROR日志-异常信息
     *
     * @param message   日志信息
     * @param throwable 异常信息
     */
    public static void error(String message, Throwable throwable) {
        if (isErrorOpen()) {
            LoggerFactory.getLogger(getLogClass()).error("exception:" + message, throwable);
        }
    }

    /**
     * ERROR日志-异常信息
     * 带参数异常
     * @param message   日志信息
     * @param throwable 异常信息
     */
    public static void error(String message, Throwable throwable,Object... params) {
        if (isErrorOpen()) {
            LoggerFactory.getLogger(getLogClass()).error("exception:" + message, params,throwable);
        }
    }

    /**
     * ERROR日志-异常信息
     * @param throwable
     */
    public static void error(Throwable throwable) {
        if (isErrorOpen()) {
                LoggerFactory.getLogger(getLogClass()).error("exception:", throwable);
        }
    }

    /**
     * WARN日志
     * 可带参，可不带参
     *
     * @param message 日志信息
     * @param params  可变参数
     */
    public static void warn(String message, Object... params) {
        if (isWarnOpen()) {
            LoggerFactory.getLogger(getLogClass()).warn(message, params);
        }
    }

    /**
     * WARN日志-异常信息
     *
     * @param message   日志信息
     * @param throwable 异常信息
     */
    public static void warn(String message, Throwable throwable) {
        if (isWarnOpen()) {
            LoggerFactory.getLogger(getLogClass()).warn("exception:" + message, throwable);
        }
    }

    /**
     * DEBUG日志
     * 可带参，可不带参
     *
     * @param message 日志信息
     * @param params  可变参数
     */
    public static void debug(String message, Object... params) {
        if (isDebugOpen()) {
            LoggerFactory.getLogger(getLogClass()).debug(message, params);
        }
    }

    /**
     * DEBUG日志-异常信息
     *
     * @param message   日志信息
     * @param throwable 异常信息
     */
    public static void debug(String message, Throwable throwable) {
        if (isDebugOpen()) {
            LoggerFactory.getLogger(getLogClass()).debug("exception:" + message, throwable);
        }
    }

    /**
     * INFO日志
     * 可带参，可不带参
     *
     * @param message 日志信息
     * @param params  可变参数
     */
    public static void info(String message, Object... params) {
        if (isInfoOpen()) {
            LoggerFactory.getLogger(getLogClass()).info(message, params);
        }
    }

    /**
     * INFO日志-异常信息
     *
     * @param message   日志信息
     * @param throwable 异常信息
     */
    public static void info(String message, Throwable throwable) {
        if (isInfoOpen()) {
            LoggerFactory.getLogger(getLogClass()).info("exception:" + message, throwable);
        }
    }

    /**
     * 获取调用工具的类
     *
     * @return
     */
    private static String getLogClass() {
        try {
            //获取运行堆栈
            StackTraceElement[] stack = Thread.currentThread().getStackTrace();
            int index = 0;
            //发现不同容器的堆栈是不一样的，所以这里动态检测下，根据本类的索引可以上推2个，可以定位到调用类
            for (int i = 0; i < stack.length; i++) {
                if (stack[i].getClassName().equals(LogUtils.class.getName())) {
                    index = i + 2;
                    break;
                }
            }
            //获取堆栈中打印类
            String className = stack[index].getClassName();
            return className;
        } catch (Exception e) {
            return LogUtils.class.getName();
        }
    }

    /**
     * 是否debug打开
     *
     * @return
     */
    public static boolean isDebugOpen() {
        return dbLogLevel.equalsIgnoreCase(DEBUG);
    }

    /**
     * 是否info打开
     *
     * @return
     */
    public static boolean isInfoOpen() {
        return dbLogLevel.equalsIgnoreCase(DEBUG) || dbLogLevel.equalsIgnoreCase(INFO);
    }

    /**
     * 是否warn打开
     *
     * @return
     */
    public static boolean isWarnOpen() {
        return dbLogLevel.equalsIgnoreCase(DEBUG) || dbLogLevel.equalsIgnoreCase(INFO) || dbLogLevel.equalsIgnoreCase(WARN);
    }

    /**
     * 是否error打开
     *
     * @return
     */
    public static boolean isErrorOpen() {
        return dbLogLevel.equalsIgnoreCase(DEBUG) || dbLogLevel.equalsIgnoreCase(INFO) || dbLogLevel.equalsIgnoreCase(WARN) || dbLogLevel.equalsIgnoreCase(ERROR);
    }

    /**
     * 获取消息编码
     *
     * @return
     */
    private static String getMsgCode() {
//获取堆栈
        try {
            //默认日志消息模板
            String msgCode = MsgCodeConst.COMMON_MSG;
            //默认操作类型
            String opt = OptType.OTHER;
            //获取运行时的类、方法、消息模板
            StackTraceElement[] stack = Thread.currentThread().getStackTrace();
            int index = 0;
            //发现不同容器的堆栈是不一样的，所以这里动态监测下，根据本类的索引可以上推两个，可以定位到调用类
            for (int i = 0; i < stack.length; i++) {
                if (stack[i].getClassName().equals(LogUtils.class.getName())) {
                    index = i + 2;
                    break;
                }
            }
            String className = stack[index].getClassName();
            //由类名反射获取类
            Class<?> clazz = Class.forName(className);
            //获取类的注解，并解析出模板编号
            if (clazz != null) {
                //获取注解
                MsgCode ma = clazz.getAnnotation(MsgCode.class);
                if (ma != null) {
                    msgCode = ma.value();
                } else {
                    //如果模板注解没有，直接返回默认模板
                    return MsgCodeConst.COMMON_MSG_OPT;
                }
            }
            //获取运行方法
            String methodName = stack[index].getMethodName();
            Method[] mds = clazz.getDeclaredMethods();
            Method method = null;
            for (int i = 0; i < mds.length; i++) {
                //如果找到运行方法
                if (methodName.equals(mds[i].getName())) {
                    method = mds[i];
                    break;
                }
            }
            //获取操作类型注解，如果没有返回默认
            if (method != null) {
                LogOpt la = method.getAnnotation(LogOpt.class);
                if (la != null) {
                    opt = la.value();
                } else {
                    return MsgCodeConst.COMMON_MSG_OPT;
                }
            }
            //返回消息模板
            return msgCode + "_" + OptType.optMsg(opt);
        } catch (Exception e) {
            LogUtils.error(e);
            return MsgCodeConst.COMMON_MSG_OPT;
        }
    }
}
