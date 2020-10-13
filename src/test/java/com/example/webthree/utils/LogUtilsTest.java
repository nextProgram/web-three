package com.example.webthree.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author lhx
 * @date 2019/9/11
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@MsgCode(MsgCodeConst.MSG_CODE_TEST)
public class LogUtilsTest {

    @Test
    public void escDebug() {
    }

    @Test
    @LogOpt(OptType.QUERY)
    public void info() {
        //正常info
        //LogUtils.info("测试info");
        //异常
        //LogUtils.info("异常info",new Exception("exception"));
        //获取messageCode信息打印
        //LogUtils.info(MsgUtils.messsage("MSG_S"));
        //通过注解获取消息码
        LogUtils.escInfo(MsgUtils.msg("MSG_E"));
        //打印业务成功日志
        AppLogUtils.appLogSuccess("sss");
    }
    @Test
    public void isDebugOpen() {
    }

    @Test
    public void isInfoOpen() {
    }
}