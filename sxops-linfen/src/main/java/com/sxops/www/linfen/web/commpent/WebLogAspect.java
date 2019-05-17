package com.sxops.www.linfen.web.commpent;

import com.alibaba.fastjson.JSONObject;
import com.sxops.www.common.component.BaseWebLogAspect;
import com.sxops.www.common.enums.OpLogSystem;
import com.sxops.www.linfen.dao.model.basic.OperateLog;
import com.sxops.www.linfen.dao.model.userInfo.UserInfo;
import com.sxops.www.linfen.service.basic.ExceptionService;
import com.sxops.www.linfen.service.basic.OperateLogService;
import com.sxops.www.linfen.service.login.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * <p> Description: [用户操作日志]</p>
 * Created on: 2017/11/1 17:10
 *
 * @author <a href="mailto: gewei@sxops.com">葛伟</a>
 * @version 1.0
 */
@Aspect
@Component
@Slf4j
public class WebLogAspect extends BaseWebLogAspect {

    @Resource
    private OperateLogService operateLogService;
    @Autowired
    private LoginService loginService;
    @Value("${server.display-name}")
    private String displayNme;
    @Autowired
    private ExceptionService exceptionService;
    @Async
    @Override
    protected void saveLog(String system, String operateDesc, String uri, String ip, String args, Date operateTime) {
        try {
            OperateLog operateLog = new OperateLog();
            //通过自定义方式获取当前用户
            UserInfo loginUser = loginService.getLoginUser();
            operateLog.setOperateDesc(operateDesc);
            operateLog.setOperateIp(ip);
            operateLog.setOperateTime(operateTime);
            operateLog.setUri(uri);
            operateLog.setOperatorName(loginUser.getUserName() + "【" + loginUser.getPhone() + "】");
            operateLog.setOperatorCode(loginUser.getUuid());
            operateLog.setRequest(args);
            operateLog.setSourceSystem(displayNme);
            requestTooLong(loginUser.getUserName(), displayNme, operateDesc, uri, ip, args, operateTime);
            operateLogService.insertSelective(operateLog);
        } catch (Exception e) {
            log.error("日志插入失败", e);
            exceptionService.handler(e);
        }

    }

    /**
     * 通过自定义的方式获取当前用户
     *
     * @param uri
     * @param args
     * @return
     */
    private String getUserInfoExtends(String uri, String args) {
        //app签到，从请求参数中获取操作人
        if (StringUtils.isNotEmpty(uri) && uri.indexOf("checkin") >= 0 && StringUtils.isNotEmpty(args)) {
            try {
                JSONObject jsonObject = JSONObject.parseObject(args);
                Object ldapObj = jsonObject.get("userCode");
                if (ldapObj != null) {
                    String userCode = (String) ldapObj;
                    return userCode;
                }
            } catch (Exception e) {
                //do nothing
            }
        }
        return null;
    }
}
