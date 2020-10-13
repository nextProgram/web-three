package com.example.webthree.utils;

/**
 * 打印日志工具类常亮
 *
 * @author lhx
 * @date 2019/9/12
 */
public final class MsgCodeConst {
    /*通用模板*/
    public static final String COMMON_MSG = "COMMON_MSG";
    public static final String COMMON_OPT = OptType.optMsg(OptType.OTHER);
    public static final String COMMON_MSG_OPT = COMMON_MSG + "_" + COMMON_OPT;
    /*业务日志操作*/
    public static final String COMM_OPT_LOG = "COMM_OPT_LOG";
    /*发生异常返回*/
    public static final String MSG_SUCCESS = "MSG_S";
    public static final String MSG_FAIL = "MSG_F";
    public static final String MSG_EXCEPTION = "MSG_E";
    /*用户管理*/
    public static final String USER = "USER";
    /*角色管理*/
    public static final String ROLE = "ROLE";
    /*登录*/
    public static final String LOGIN = "LOGIN";
    /*菜单*/
    public static final String MENU = "MENU";
    /*字典*/
    public static final String DICT = "DICT";
    /*数据查询绿色通道*/
    public static final String DATAGREEN = "DATAGREEN";
    /*数据运维绿色通道*/
    public static final String DATAMAINTAIN = "DATAMAINTAIN";
    /*测试*/
    public static final String MSG_CODE_TEST = "MSG_CODE_TEST";
}
