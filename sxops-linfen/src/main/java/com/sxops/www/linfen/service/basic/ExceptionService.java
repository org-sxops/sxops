package com.sxops.www.linfen.service.basic;

import com.sxops.www.linfen.dao.model.basic.ExceptionLog;

import javax.servlet.http.HttpServletRequest;

/**
 * <p> Description: [异常处理服务]</p>
 * Created on: 2017/11/2 16:05
 *
 * @author <a href="mailto: gewei@sxops.com">葛伟</a>
 * @version 1.0
 */
public interface ExceptionService {

    void handler( Exception e,String message);
}
