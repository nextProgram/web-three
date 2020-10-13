package com.example.webthree.base.spring;

import com.example.webthree.utils.MessageCode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

/**
 * @author lhx
 * @date 2019/9/13
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PropertyConfigurerTest {

    /**
     * {@link com.example.webthree.base.spring.PropertyConfigurer# getMsgs()}.
     */
    @Test
    public void getMsgs() {
        Map<String, MessageCode> aa = PropertyConfigurer.getMsgs();
        System.out.println(aa);
    }

}