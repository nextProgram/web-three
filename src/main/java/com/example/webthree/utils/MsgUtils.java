package com.example.webthree.utils;

import com.example.webthree.base.spring.PropertyConfigurer;
import org.springframework.beans.BeanUtils;

/**
 * 错误码帮助类
 *
 * @author lhx
 * @date 2019/9/10
 */
public class MsgUtils {
    /**
     * 获取消息码对应消息
     * 1.不传参数：根据消息码获取XML的消息
     * 2.传参数：此参数为消息占位符的参数将参数拼接到占位符，再返回消息
     * 3.传参数目前用于存入业务日志表
     * @param code 消息码
     * @param params 参数，可传可不传
     * @return
     */
    public static MessageCode msg(String code,Object... params) {
        MessageCode messageCode = PropertyConfigurer.getMsgs().get(code);
        String msg = "";
        if (messageCode == null) {
            //不存在消息码，则将messageCode所有属性设置为code
            messageCode = new MessageCode();
            messageCode.setMsg(code);
            messageCode.setCode(code);
            messageCode.setModelName(code);
            messageCode.setType(code);
            messageCode.setTypeName(code);
            msg = code;
        } else if (params.length > 0) {
            //参数化处理
            msg = MsgFormatter.arrayFormatter(messageCode.getMsg(),params);
        }else {
            msg = messageCode.getMsg();
        }
        //由于MessageCode市内存中存放的快照，所以MessageCode改变会导致内存快照改变
        MessageCode temp = new MessageCode();
        temp.setMsg(msg);
        temp.setCode(messageCode.getCode());
        temp.setModelName(messageCode.getModelName());
        temp.setType(messageCode.getType());
        //BeanUtils.copyProperties(messageCode,temp);
        return temp;
    }

    /**
     * 获取消息
     * @param code 消息码
     * @return
     */
    public static String messsage(String code){
        MessageCode msg = msg(code);
        return msg == null ? code : msg.getMsg();
    }
}
