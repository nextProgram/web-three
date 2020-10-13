package com.example.webthree.base.spring;

import com.example.webthree.utils.LogUtils;
import com.example.webthree.utils.MessageCode;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * 配置文件初始化类：用于spring加载配置文件
 *
 * @author lhx
 * @date 2019/9/11
 */
@Component
public class PropertyConfigurer extends PropertyPlaceholderConfigurer {
    //配置文件资源
    private static Properties p = new Properties();
    //消息码类，在IOC中的name
    private static Map<String, MessageCode> msgs = new HashMap<String, MessageCode>();
    //消息码资源,作用等同于@Resource
    //private Resource[] messageCodes = null;

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException {
        super.processProperties(beanFactoryToProcess, props);
        //暴露配置文件，方便程序使用
        p.putAll(props);
        try {
            LogUtils.info("Loading message code starting");
            //获取messageCode.xml
            ClassPathResource classPathResource = new ClassPathResource("messageCode.xml");
            msgs.putAll(parseXml(classPathResource.getInputStream()));

            //for (Resource resource : messageCodes) {
            //LogUtils.info("======file message:" + resource.getFilename());
            //msgs.putAll(parseXml(this.getClass().getResourceAsStream("messageCode.xml")));
            //parseXml(resource.getInputStream());
            //}
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.error(e.getMessage());
        }
    }

    /**
     * 解析messageCode.xml
     *
     * @return
     */
    private Map<String, MessageCode> parseXml(InputStream inputStream) throws DocumentException {
        //SAX解析
        Map<String, MessageCode> resultMap = new HashMap<String, MessageCode>();
        SAXReader reader = new SAXReader();
        Document doc = reader.read(inputStream);
        Element root = doc.getRootElement();
        List<?> elements = root.elements();
        //循环加载消息码
        for (Object obj : elements) {
            Element messageElement = (Element) obj;
            MessageCode messageCode = new MessageCode();
            messageCode.setCode(messageElement.attributeValue("code"));
            messageCode.setMsg(messageElement.getText());
            messageCode.setModelName(messageElement.attributeValue("modelname"));
            if (!StringUtils.isEmpty(messageElement.attributeValue("type"))) {
                messageCode.setType(messageElement.attributeValue("type"));
            } else {
                messageCode.setType("info");
            }
            if (resultMap.containsKey(messageElement.attributeValue("code"))) {
                LogUtils.info("duplicate code:{},this will replace to the older", messageElement.attributeValue("code"));
            }
            resultMap.put(messageElement.attributeValue("code"), messageCode);
        }
        return resultMap;
        //jackson解析
        /*Map<String, MessageCodeDetail> resultMap = new HashMap<String, MessageCodeDetail>();
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        //忽略pojo中不存在的字段
        xmlMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);

        try {
            MessageCode<MessageCodeDetail> message = xmlMapper.readValue(inputStream, MessageCode.class);
            List<MessageCodeDetail> msgCodeDetailList = message.getMessage();
            for (int i = 0; i < msgCodeDetailList.size(); i++) {
                resultMap.put(msgCodeDetailList.get(i).getCode(), msgCodeDetailList.get(i));
            }
            System.out.println(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultMap;*/
    }

    /**
     * 获取属性值
     * @param key
     * @return
     */
    public static String getValue(String key) {
        return p.getProperty(key);
    }

    public static Map<String, MessageCode> getMsgs() {
        return msgs;
    }
}
