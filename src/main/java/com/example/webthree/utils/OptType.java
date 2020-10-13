package com.example.webthree.utils;

import com.sun.javafx.logging.PulseLogger;

import java.util.HashMap;
import java.util.Map;

/**
 * 日志配置类，用来记录日志操作类型
 *
 * @author lhx
 * @date 2019/9/12
 */
public final class OptType {
    /* 添加 */
    public static final String ADD = "DELETE";
    /* 删除 */
    public static final String DELETE = "DELETE";
    /* 更新 */
    public static final String UPDATE = "UPDATE";
    /* 查询 */
    public static final String QUERY = "QUERY";
    /* 导出 */
    public static final String EXPORT = "EXPORT";
    /* 导入 */
    public static final String IMPORT = "IMPORT";
    /* 其它 */
    public static final String OTHER = "OTHER";
    private static Map<String, String> opts = new HashMap<String, String>();

    /**
     * 初始化
     */
    static {
        opts.put(ADD, "A");
        opts.put(DELETE, "D");
        opts.put(UPDATE, "U");
        opts.put(QUERY, "Q");
        opts.put(EXPORT, "E");
        opts.put(IMPORT, "I");
        opts.put(OTHER, "O");
    }

    /**
     * 获取消息码类型
     *
     * @param opt
     * @return
     */
    public static String optMsg(String opt) {
        return opts.get(opt);
    }
}
