package com.example.webthree.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lhx
 * @date 2019/10/29
 */
public class MsgFormatter {
    static final String DELIM_START = "{";
    static final String DELIM_STOP = "}";
    static final String DELIM_STR = "{}";
    private static final char ESCAPE_CHAR = '\\';

    /**
     * 格式化信息方法
     * @param messagePattern
     * @param argArray
     * @return
     */
    public static final String arrayFormatter(final String messagePattern, final Object[] argArray) {
        if (messagePattern == null || argArray == null) {
            return null;
        }
        int i = 0;
        int j = 0;
        final int sbufAppendSize = 50;
        StringBuffer sbuf = new StringBuffer(messagePattern.length() + sbufAppendSize);

        int l = 0;
        for (l = 0; l < argArray.length; l++) {
            j = messagePattern.indexOf(DELIM_STR, i);
            if (j == -1) {
                //no more variables
                if (i == 0) {
                    //this is a simple string
                    return messagePattern;
                } else {
                    //add the tail string which contains no variables and return the result
                    sbuf.append(messagePattern.substring(i, messagePattern.length()));
                    return sbuf.toString();
                }
            } else {
                if (isEscapedDelimeter(messagePattern, j)) {
                    if (!isDoubleEscaped(messagePattern, j)) {
                        l--;
                        sbuf.append(messagePattern.substring(i, j - 1));
                        sbuf.append(DELIM_START);
                        i = j + 1;
                    } else {
                        sbuf.append(messagePattern.substring(i,j - 1));
                        deeplyAppendParameter(sbuf,argArray[l],new HashMap<Object[], Object>());
                        i = j + 2;
                    }
                }else {
                    sbuf.append(messagePattern.substring(i,j));
                    deeplyAppendParameter(sbuf,argArray[l],new HashMap<Object[], Object>());
                    i = j + 2;
                }
            }
        }
        sbuf.append(messagePattern.substring(i,messagePattern.length()));
        if (l < argArray.length - 1){
            return sbuf.toString();
        }else {
            return sbuf.toString();
        }
    }

    /**
     * 判断是否为转义字符
     *
     * @param messagePattern
     * @param delimeterStartIndex
     * @return
     */
    private static final boolean isEscapedDelimeter(String messagePattern, int delimeterStartIndex) {
        if (delimeterStartIndex == 0) {
            return false;
        }
        char potentialEscape = messagePattern.charAt(delimeterStartIndex - 1);
        return potentialEscape == ESCAPE_CHAR;
    }

    private static final boolean isDoubleEscaped(String messagePattern, int delimeterStarIndex) {
        final int idxStart = 2;
        return delimeterStarIndex >= idxStart && messagePattern.charAt(delimeterStarIndex - idxStart) == ESCAPE_CHAR;
    }

    private static void safeObjectAppend(StringBuffer sbuf,Object obj){
        try {
            String objAsString = obj.toString();
            if(obj instanceof Date){
                objAsString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(obj);
            }
            if(obj instanceof Throwable){
                objAsString = ((Throwable)obj).getMessage();
            }
            sbuf.append(objAsString);
        }catch (Exception t){
            LogUtils.error(t);
            sbuf.append("[FAILED TO STRING]");
        }
    }

    private static void objectArrayAppend(StringBuffer sbuf, Object[] a, Map<Object[],Object> seenMap){
        sbuf.append('[');
        if (!seenMap.containsKey(a)){
            seenMap.put(a,null);
            final int len = a.length;
            for (int i = 0; i < len; i++) {
                deeplyAppendParameter(sbuf,a[i],seenMap);
                if(i != len - 1){
                    sbuf.append(",");
                }
            }
            seenMap.remove(a);
        }else {
            sbuf.append("...");
        }
        sbuf.append(']');
    }

    private static void deeplyAppendParameter(StringBuffer sbuf,Object o,Map<Object[],Object> seenMap){
        if(o == null){
            sbuf.append("null");
            return;
        }
        if(!o.getClass().isArray()){
            safeObjectAppend(sbuf,o);
        }else {
            objectArrayAppend(sbuf,(Object[]) o,seenMap);
        }
    }
}
