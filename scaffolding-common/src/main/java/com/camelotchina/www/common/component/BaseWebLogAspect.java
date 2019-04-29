package com.camelotchina.www.common.component;

import com.alibaba.fastjson.JSON;
import com.camelotchina.www.common.enums.OpLogMethod;
import com.camelotchina.www.common.util.date.DateStyle;
import com.camelotchina.www.common.annotation.OpLog;
import com.camelotchina.www.common.enums.OpLogSystem;
import com.camelotchina.www.common.util.NetUtil;
import com.camelotchina.www.common.util.StringUtils;
import com.camelotchina.www.common.util.date.DateUtils;
import io.swagger.annotations.ApiOperation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * <p> Description: [记录系统日志]</p>
 * Created on: 2017/10/31 16:14
 * @author <a href="mailto: liruifeng@camelotchina.com">尹归晋</a>
 * @version 1.0
 */

public class BaseWebLogAspect {
    protected  Logger LOGGER = LoggerFactory.getLogger(getClass());

    private static String LOG_TEMPLATE = "操作人%s \n URL:%s \n 时间:%s \n 描述:%s \n 系统:%s \n 操作人ip:%s \n 请求参数：%s\n";

    @Pointcut(value = "@annotation(opLog)",argNames = "opLog")
    public void webLog(OpLog opLog){}

    @Before(value = "webLog(opLog)")
    protected void controllerLog(JoinPoint point, OpLog opLog) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //请求方法中文描述
        String methodDesc = getOperateDesc(point);
        if(StringUtils.isNotEmpty(opLog.desc())){
            methodDesc = opLog.desc();
        }
        //请求uri
        String uri = request.getRequestURI();
        //请求人ip
        String ip = NetUtil.getIpAddr(request);
        //请求参数
        String args = null;
        //系统
        String system =null;
        //操作时间
        Date operateTime = new Date();

        if(opLog.method() == OpLogMethod.NORMAL_AND_REQUEST) {
            Enumeration<String> parameterNames = request.getParameterNames();
            Map<String,String> requestParams = new HashMap<>();
            while (parameterNames.hasMoreElements()){
                String argName = parameterNames.nextElement();
                String value = request.getParameter(argName);
                requestParams.put(argName,value);
            }
            args = JSON.toJSONString(requestParams);
        }
        if(opLog.system() != OpLogSystem.NULL){
            system = opLog.system().toString();
        }
        saveLog(system,methodDesc,uri,ip,args,operateTime);
    }



    /**
     * <p>Discription: [获取方法的中文说明，注解ApiOperation说明] </p>
     * Created on: 2017/11/1 15:07
     * @param joinPoint
     * @return String 中文说明
     * @author [尹归晋]
     */
    protected  static String getMethodDesc(JoinPoint joinPoint)  throws Exception {
        String description = "";
        ApiOperation annotation = joinPoint.getTarget().getClass().getAnnotation(ApiOperation.class);
        if(annotation!=null){
            description = annotation.value();
        }
        return description;
    }
    /**
     * <p>Discription: [持久化操作日志] </p>
     * Created on: 2017/11/10 15:33
     * @param system 系统
     * @param operateDesc 操作描述
     * @param uri 请求路径
     * @param ip 操作人ip
     * @param args 请求参数
     * @param operateTime 操作时间
     * @author [尹归晋]
     */
    protected void saveLog(String system, String operateDesc, String uri, String ip, String args,Date operateTime){
        LOGGER.info("操作描述方法：{}，uri：{}，用户ip：{}，请求：{}，操作时间：{}",operateDesc,uri,ip,args,operateTime.toString());
    }

    /**
     * <p>Discription: [获取方法的中文说明，注解ApiOperation说明] </p>
     * Created on: 2017/11/1 15:07
     * @param joinPoint
     * @return String 中文说明
     * @author [尹归晋]
     */
    protected  static String getOperateDesc(JoinPoint joinPoint)  throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    ApiOperation annotation = method.getAnnotation(ApiOperation.class);
                    if(annotation!=null){
                        description = annotation.value();
                    }
                    break;
                }
            }
        }
        return description;
    }

    protected void requestTooLong(String userName,String system, String operateDesc, String uri, String ip, String args, Date operateTime){
        //text长度为65535（64K）,utf8一般占用三个字节，因此长度截取为20000
        if(StringUtils.isNotEmpty(args)&&args.length()>20000) {
            args = args.substring(0,20000);
            String request = String.format(LOG_TEMPLATE, userName, uri, DateUtils.DateToString(operateTime, DateStyle.YYYY_MM_DD_HH_MM_SS), operateDesc, system, ip, args);
            LOGGER.error(request);
        }
    }


}
