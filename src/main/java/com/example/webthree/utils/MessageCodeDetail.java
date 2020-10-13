package com.example.webthree.utils;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author lhx
 * @date 2019/10/14
 */
@Data
public class MessageCodeDetail {
    @JacksonXmlProperty(isAttribute = true, localName = "code")
    private String code;//消息码
    @JacksonXmlText
    private String msg;//消息内容
    @JacksonXmlProperty(isAttribute = true, localName = "modelname")
    private String modelName;//模块名称
    @JacksonXmlProperty(isAttribute = true, localName = "type")
    private String type;//消息类型
    private String typeName;//消息类型名称
}
