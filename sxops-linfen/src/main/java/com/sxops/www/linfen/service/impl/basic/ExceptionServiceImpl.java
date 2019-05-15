package com.sxops.www.linfen.service.impl.basic;

import com.sxops.www.common.util.NetUtil;
import com.sxops.www.linfen.dao.model.basic.ExceptionLog;
import com.sxops.www.linfen.service.basic.ExceptionLogService;
import com.sxops.www.linfen.service.basic.ExceptionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.util.Date;

/**
 * <p> Description: [异常处理服务]</p>
 * Created on: 2017/11/2 16:07
 *
 * @author <a href="mailto: liruifeng@sxops.com">尹归晋</a>
 * @version 1.0
 */
@Component
public class ExceptionServiceImpl implements ExceptionService {


    @Value("${server.display-name}")
    private String serverName;

    @Resource
    private ExceptionLogService exceptionLogService;


    private String mailTemplate = "操作人：%s\n" +
            "系统：%s\n" +
            "URL：%s\n" +
            "IP：%s\n" +
            "操作时间：%s\n" +
            "错误描述：%s\n" +
            "浏览器信息：%s\n" +
            "错误详情：%s";

    @Override
    public void handler(Object user, String exceptionMsg, Exception e, HttpServletRequest request) {
        try {
            // 如果异常时用来提示的，那么什么都不做
          /*  if (e instanceof WarnException) {
                return;
            }*/
            ExceptionLog exceptionLog = new ExceptionLog();
            exceptionLog.setServerName(serverName);
            exceptionLog.setOperator(serverName);
            exceptionLog.setDescription(exceptionMsg);
            if (request != null) {
                exceptionLog.setOperatorIp(NetUtil.getIpAddr(request));
                exceptionLog.setUri(request.getRequestURI());
                exceptionLog.setBrowerMessage(request.getHeader("User-Agent"));
            }
            if (user != null) {
                exceptionLog.setOperatorCode(StringUtils.defaultString("gewei"));
            }
            //输出错误堆栈
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PrintStream ps = new PrintStream(outputStream);
            e.printStackTrace(ps);
            exceptionLog.setDetail(outputStream.toString());
            exceptionLog.setCreateTime(new Date());
            exceptionLog.setHostName(InetAddress.getLocalHost().getHostName());
            exceptionLogService.insert(exceptionLog);
            sendMail(exceptionLog);
        } catch (Exception e1) {
            // LOGGER.warn(LogMessage.getNew().add("存储异常日志失败", e1.getMessage()).toString());
        }
    }

    /*   */


    @Override
    public void sendMail(ExceptionLog exceptionLog) {

    }

    @Override
    public void handler(String exceptionMsg, Exception e, HttpServletRequest request) {

    }

    @Override
    public void handler(String userCode, String exceptionMsg, Exception e, HttpServletRequest request) {

    }

    @Override
    public void handler(String exceptionMsg, Exception e) {

    }


}
