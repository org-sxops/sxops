package com.camelotchina.www.service;

import com.camelotchina.www.dao.model.ExceptionLog;

import javax.servlet.http.HttpServletRequest;

/**
 * <p> Description: [异常处理服务]</p>
 * Created on: 2017/11/2 16:05
 *
 * @author <a href="mailto: liruifeng@camelotchina.com">尹归晋</a>
 * @version 1.0
 */
public interface ExceptionService {

    /**
     * <p>Discription: [错误处理] </p>
     * Created on: 2017/11/6 14:22
     * @param user 用户信息
     * @param exceptionMsg  错误描述
     * @param e  错误对象
     * @param request  请求对象对象
     * @author [尹归晋]
     */
    void handler(Object user, String exceptionMsg, Exception e, HttpServletRequest request);

    /**
     * 发送异常邮件
     * @param exceptionLog
     */
    void sendMail(ExceptionLog exceptionLog);

    /**
     * <p>Discription: [错误处理] </p>
     * Created on: 2017/11/6 14:22
     * @param exceptionMsg  错误描述
     * @param e  错误对象
     * @param request  请求对象对象
     * @author [尹归晋]
     */
    void handler(String exceptionMsg, Exception e, HttpServletRequest request);

    void handler(String userCode, String exceptionMsg, Exception e, HttpServletRequest request);

    /**
     * <p>Discription: [错误处理] </p>
     * Created on: 2017/11/6 14:22
     * @param exceptionMsg  错误描述
     * @param e  错误对象
     * @author [尹归晋]
     */
    void handler(String exceptionMsg, Exception e);
}
