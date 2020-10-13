package com.example.webthree.utils;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author lhx
 * @date 2019/9/10
 */

@Data
//@JacksonXmlRootElement(localName = "messages")
public class MessageCode {

  /*  @JacksonXmlElementWrapper(localName = "messages")
    @JacksonXmlProperty(localName = "message")
    private List<MessageCodeDetail> message;*/
    //@JacksonXmlProperty(isAttribute = true, localName = "code")
    private String code;//消息码
    //@JacksonXmlText
    private String msg;//消息内容
    private String modelName;//模块名称
    private String type;//消息类型
    private String typeName;//消息类型名称
}
