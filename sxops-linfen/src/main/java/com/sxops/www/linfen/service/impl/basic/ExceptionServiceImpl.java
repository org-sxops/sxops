package com.sxops.www.linfen.service.impl.basic;

import com.sxops.www.basicException.AbstractBasicException;
import com.sxops.www.common.util.NetUtil;
import com.sxops.www.linfen.dao.model.basic.ExceptionLog;
import com.sxops.www.linfen.dao.model.userInfo.UserInfo;
import com.sxops.www.linfen.service.basic.ExceptionLogService;
import com.sxops.www.linfen.service.basic.ExceptionService;
import com.sxops.www.linfen.service.login.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

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
 * @author <a href="mailto: gewei@sxops.com">葛伟</a>
 * @version 1.0
 */
@Component
@Slf4j
public class ExceptionServiceImpl implements ExceptionService {


    @Resource
    private ExceptionLogService exceptionLogService;

    @Autowired
    private LoginService loginService;



    @Override
    public void handler(Exception e,String message) {
        try {
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = servletRequestAttributes.getRequest();
            ExceptionLog exceptionLog = new ExceptionLog();
            String exceptionMsg;
            if (e instanceof AbstractBasicException) {
                AbstractBasicException basicException = (AbstractBasicException) e;
                exceptionMsg = "[" + basicException.getCode() + "]" + basicException.getMessage();
                exceptionLog.setDescription(exceptionMsg);
            } else {
                exceptionLog.setDescription("其他异常信息");
            }
            exceptionLog.setExceptionType(message);

            if (request != null) {
                exceptionLog.setOperatorIp(NetUtil.getIpAddr(request));
                exceptionLog.setUri(request.getRequestURI());
                exceptionLog.setBrowerMessage(request.getHeader("User-Agent"));
            }
            UserInfo userInfo = loginService.getLoginUser();
            if (userInfo != null) {
                String username = userInfo.getUserName() + "【" + userInfo.getPhone() + "】";
                exceptionLog.setOperatorCode(userInfo.getUuid());
                exceptionLog.setOperator(username);
            }
            //输出错误堆栈
            exceptionLog.setDetail(e.toString());
            exceptionLog.setCreateTime(new Date());
            exceptionLog.setHostName(InetAddress.getLocalHost().getHostName());
            exceptionLogService.insert(exceptionLog);
        } catch (Exception e1) {
            log.warn("存储异常日志失败", e1);
        }

    }
}
