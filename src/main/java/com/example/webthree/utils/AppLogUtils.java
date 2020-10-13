package com.example.webthree.utils;

import com.example.webthree.login.UserInSession;
import com.example.webthree.system.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * 业务日志工具类
 *
 * @author lhx
 * @date 2019/10/29
 */
public class AppLogUtils {

    //接口业务日志的用户名
    public static final String INTERFACE_USER = "INT_USER";

    /**
     * 添加业务操作成功日志
     * @param params
     */
    public static void appLogSuccess(Object... params) {
        //获取消息码及调用堆栈信息
        String[] mss = getMsgCode();
        if (mss == null) {
            LogUtils.error("记录日志失败");
            return;
        }
        //获取日志工具类
        Logger logger = LoggerFactory.getLogger(mss[4]);
        //拼装
        MessageCode msg = MsgUtils.msg(mss[3] + "_S",params);
        //在记录业务日之前，先打印程序日志
        logger.info(msg.getMsg());
        //记录业务日志
        appLog(mss[0],mss[1],mss[2],LogUtils.INFO,msg.getMsg());
    }

    /**
     * 添加业务日志
     * @param className 日志类
     * @param mthodName 日志方法
     * @param optType 操作类型
     * @param level 日志级别 目前info和debug
     * @param logContent 日志中内容
     */
    private static void appLog(String className,String mthodName,String optType,String level,String logContent){
        //获取当前用户
        String userId = getUserId();
        if (userId == null) {
            userId = INTERFACE_USER;
        }else {
            //根据参数和消息模板获取完整消息
            MessageCode messageCode = MsgUtils.msg(MsgCodeConst.COMM_OPT_LOG,userId,(className + "." + mthodName),optType,logContent);
            String comtengs = messageCode.getMsg();
            //如果日志超长，需要截取
            if (comtengs.getBytes().length > 2000) {
                comtengs = comtengs.substring(0,2000);
            }
            //业务日志入库
            try {

            }catch (Exception e){
                LogUtils.error("保存业务日志失败:[{}]",comtengs,e);
            }
        }
    }

    /**+
     * 获取当前登录用户ID，用来记录业务日志
     * @return 登录用于ID
     */
    private static String getUserId() {
        ServletRequestAttributes atts = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest req = atts.getRequest();
        UserInSession uis = (UserInSession) req.getSession().getAttribute(Constants.USER_IN_SESSION);
        //返回当前用户
        if (uis != null) {
            return uis.getUserId();
        }else {
            return null;
        }
    }

    /**
     * 获取打印日志类的相关信息，用于记录日志
     * rtn 0:classNameShortname,1:methodName,2:opttype,3:messageCode,4:className
     *
     * @return 堆栈信息数组
     */
    private static String[] getMsgCode() {
        String[] rtn = new String[5];
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
                if (stack[i].getClassName().equals(AppLogUtils.class.getName())) {
                    index = i + 2;
                    break;
                }
            }
            //获取调用类
            String className = stack[index].getClassName();
            rtn[4] = className;
            if (className.trim().length() > 0 && className.lastIndexOf(".") > 0) {
                rtn[0] = className.substring(className.lastIndexOf(".") + 1);
            } else {
                rtn[0] = className;
            }
            //获取运行方法
            String methodName = stack[index].getMethodName();
            rtn[1] = methodName;
            //设置默认值
            rtn[2] = OptType.OTHER;
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
                    rtn[3] = MsgCodeConst.COMMON_MSG_OPT;
                    return rtn;
                }
            }
            //反射查找方法
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
                    rtn[2] = opt;
                } else {
                    rtn[3] = MsgCodeConst.COMMON_MSG_OPT;
                    return rtn;
                }
            }
            //正常消息模板
            rtn[3] = msgCode + "_" + OptType.optMsg(opt);
            return rtn;
        } catch (Exception e) {
            LogUtils.error(e);
            return null;
        }
    }
}
