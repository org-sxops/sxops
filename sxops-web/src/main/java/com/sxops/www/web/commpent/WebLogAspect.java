package com.sxops.www.web.commpent;

import com.alibaba.fastjson.JSONObject;
import com.sxops.www.common.component.BaseWebLogAspect;
import com.sxops.www.dao.model.OperateLog;
import com.sxops.www.service.OperateLogService;
import com.sxops.www.common.enums.OpLogSystem;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * <p> Description: [用户操作日志]</p>
 * Created on: 2017/11/1 17:10
 *
 * @author <a href="mailto: liruifeng@sxops.com">尹归晋</a>
 * @version 1.0
 */
@Aspect
@Component
public class WebLogAspect extends BaseWebLogAspect {

    @Resource
    private OperateLogService operateLogService;

    @Async
    @Override
    protected void saveLog(String system, String operateDesc, String uri, String ip, String args, Date operateTime) {
        try {
            OperateLog operateLog = new OperateLog();
            //通过自定义方式获取当前用户
            String userCode = getUserInfoExtends(uri, args);
            //获取sso当前用户
            String userName = "系统调用";
            if(userCode!=null){
                operateLog.setOperatorCode(StringUtils.defaultString(userCode));
                userName = userCode;
            }
            operateLog.setOperateDesc(operateDesc);
            if(system==null){
                operateLog.setSystem(OpLogSystem.WEB.toString());
            }
            operateLog.setOperateIp(ip);
            operateLog.setOperateTime(operateTime);
            operateLog.setUri(uri);
            operateLog.setOperateIp(ip);
            operateLog.setRequest(args);
            requestTooLong(userName, system, operateDesc, uri, ip, args, operateTime);
            operateLogService.insert(operateLog);
        }catch (Exception e){
            LOGGER.error(e.toString());
        }

    }

    /**
     * 通过自定义的方式获取当前用户
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
