package com.camelotchina.www.scheduler.job;


import com.camelotchina.www.scheduler.util.HttpClientUtils;
import com.camelotchina.www.scheduler.util.Md5Util;
import com.camelotchina.www.scheduler.util.RedisDB;
import com.camelotchina.www.scheduler.util.SpringUtil;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>Description: [时调度任务]</p>
 * Copyright (c) 2017 北京柯莱特科技有限公司
 * Created on 2017年11月10日
 *
 * @author <a href="mailto: yinguijin@camelotchina.com">尹归晋</a>
 * @version 1.0
 */
public class MyJob implements Job {
	
    private String serviceName = "MEETING_SCHEDULER_";

    private RedisDB redisDB;

    /** redis锁有效时间 */
    private static final long REDIS_LOCK_MINUTES = 10L;

    /** 日志 */
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(MyJob.class);

    /**
     * Discription:[任务执行操作]
     * @param jobExecutionContext job任务执行上下文
     *
     * Created on 2017/11/10
     * @author: 尹归晋
     */
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        if (redisDB == null){
            redisDB = SpringUtil.getBean(RedisDB.class);
        }
        JobDataMap data = jobExecutionContext.getMergedJobDataMap();
        String apiName = data.get("name") + "";
        String apiUrl = data.get("url") + "";
        String apiMethod = data.get("method") + "";
        Map<String, Object> apiParams = (Map<String, Object>) data.get("params");
        LOGGER.info("\n\n JOBapiUrl加redis锁开始" + apiUrl);
        String redisKey = serviceName + Md5Util.strToMd5(apiUrl + serviceName);
        LOGGER.info("\n redis锁key" + redisKey);
        boolean lock = redisDB.lock(redisKey, REDIS_LOCK_MINUTES);
        Long expire = redisDB.getExpire(redisKey);
        LOGGER.info("\n redis锁expire" + expire);
        if(expire<0){
            redisDB.del(redisKey);
        }
        LOGGER.info("\n exists检查redis锁是否存在" +  redisDB.exists(redisKey) + "；lock结果" + lock);
        try {
            LOGGER.info("是否获取到锁" + lock);
            if (lock) {
                String result = sendRequest(apiName, apiUrl, apiMethod, apiParams);
                //记录日志
                LOGGER.info("\n 方法[{}]，入参：[{}]", "MyJob-execute", "apiName:" + apiName + ",apiUrl:" + apiUrl + ",apiMethod:" + apiMethod + ",apiParams:" + apiParams);
                //输出提示信息
                Date date = new Date();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String timeStr = format.format(date);
                LOGGER.info("定时任务系统于" + timeStr + "调用:" + apiName+"  "+apiUrl+"	"+result);
            }
        } catch (Exception e) {
            LOGGER.info("定时任务执行报错" + e);
        } finally {
            if (lock) {
                redisDB.del(redisKey);
            } else {
                LOGGER.info("没有获取到锁，无需释放");
            }
        }
    }

    /**
     * Discription:[发送Restful请求]
     * @param apiName 请求接口名
     * @param apiUrl 请求接口地址
     * @param apiMethod 请求接口方式（GET、POST）
     * @param apiParams 请求参数
     * @return 接口响应结果
     *
     * Created on 2017/11/10
     * @author: 尹归晋
     */
    private String sendRequest(String apiName, String apiUrl, String apiMethod, Map<String, Object> apiParams) {
        LOGGER.info("\n 方法[{}]，入参：[{}]", "MyJob-sendRequest", "apiName:" + apiName + ",apiUrl:" + apiUrl + ",apiMethod:" + apiMethod + ",apiParams:" + apiParams);
        String paramStr = "";
        if (apiParams != null) {
            for (Map.Entry<String, Object> entry : apiParams.entrySet()) {
            	if (entry.getValue() != null && "${yestoday}".equals(entry.getValue())) {
            		//获取昨天的日期
            		Calendar cal=Calendar.getInstance();
                    cal.add(Calendar.DATE,-1);
                    Date d=cal.getTime();
                    SimpleDateFormat sp=new SimpleDateFormat("yyyy-MM-dd");
                    paramStr=sp.format(d);//获取昨天日期
            	}
            	
                if (entry.getValue() != null && "${now}".equals(entry.getValue())) {
                    paramStr += entry.getKey() + "=" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + "&";
                } else {
                    paramStr += entry.getKey() + "=" + entry.getValue() + "&";
                }
            }
        }
        if ("GET".equals(apiMethod.toUpperCase())) {
            return HttpClientUtils.sendGet(apiUrl, paramStr);
        } else if ("POST".equals(apiMethod.toUpperCase())) {
            Map<String, String> headers = new HashMap<>();
            return HttpClientUtils.sendPost(apiUrl, paramStr, headers);
        }
        return null;
    }
}
