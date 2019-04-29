package com.camelotchina.www.scheduler.task;

import com.camelotchina.www.common.util.log.LogMessage;
import com.camelotchina.www.scheduler.job.MyJob;
import com.camelotchina.www.scheduler.util.XmlParseUtils;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;


/**
 * <p>Description: [任务]</p>
 * Copyright (c) 2017 北京柯莱特科技有限公司
 * Created on 2017年11月10日
 *
 * @author <a href="mailto: yinguijin@camelotchina.com">尹归晋</a>
 * @version 1.0
 */
@Component
public class MyTask {

    /** 日志 */
    private static final org.slf4j.Logger LOGGER = LoggerFactory
            .getLogger(MyTask.class);

    @Value("${scheduler.file}")
    private String schedulerFile;

    /** 任务调度对象 */
    @Autowired
    private Scheduler scheduler;

    /**
     * Discription:[启动任务]
     *
     * Created on 2017/11/10
     * @author: 尹归晋
     */
    public void taskStart() {
        try {
            scheduler.start();
            Map<String, Object> task = getTasks();
            Object schedulers = task.get("schedulers");
            if (schedulers == null || !(schedulers instanceof List)) {
                LOGGER.error(LogMessage.getNew().add("方法[MyTask-taskStart]，错误：scheduler.xml配置文件错误,schedulers标签不存在").toString());
                return;
            }
            List<Map<String, Object>> children = (List<Map<String, Object>>) schedulers;
            for (int i = 0; i < children.size(); i++) {
                Map<String, Object> node = children.get(i);
                Map<String, Object> scheduler = (Map<String, Object>) node.get("scheduler");
                String jobName = scheduler.get("job-name") + "";
                String jobGroupName = scheduler.get("job-group") + "";
                JobDetail job = newJob(MyJob.class)
                        .withIdentity(jobName, jobGroupName)
                        .build();
                String triggerName = scheduler.get("trigger-name") + "";
                String triggerGroupName = scheduler.get("trigger-group") + "";
                String cron = scheduler.get("cron") + "";
                LOGGER.error(LogMessage.getNew().add("\n\n\n"+ jobGroupName + "   " + triggerName + "   " + jobName + "   " + triggerGroupName + "   "  + cron).toString());
                LOGGER.error(LogMessage.getNew().add("\n"+ jobName + "   " + cron).toString());
                Trigger trigger = newTrigger()
                        		  .withIdentity(triggerName, triggerGroupName)
                        		  .withSchedule(cronSchedule(cron))
                        		  .forJob(job)
                        		  .build();
                Object resourceObj = scheduler.get("resource");
                if (resourceObj == null || !(resourceObj instanceof Map)) {
                    LOGGER.error(LogMessage.getNew().add("\n方法[MyTask-taskStart]，错误：[scheduler.xml配置文件错误, resource标签下无内容]").toString());
                    return;
                }
                Map<String, Object> resource = (Map<String, Object>) resourceObj;
                job.getJobDataMap().put("name", resource.get("name"));
                job.getJobDataMap().put("url", resource.get("url"));
                job.getJobDataMap().put("method", resource.get("method"));
                job.getJobDataMap().put("params", resource.get("params"));
                this.scheduler.scheduleJob(job, trigger);
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
            LOGGER.info("\n 方法[{}]，异常：[{}]", "MyTask-taskStart", e.getMessage());
        }
    }


    /**
     * Discription:[读取scheduler.xml资源文件，获取任务信息]
     * @return 返回任务内容。格式：{name: xx, children: [{name: xx, value: xx}]}
     *
     * Created on 2017/11/10
     * @author: 尹归晋
     */
    public Map<String, Object> getTasks() {
        InputStream file = getClass().getClassLoader().getResourceAsStream(schedulerFile);
        Map<String, Object> xmlResult = XmlParseUtils.parse(file);
        return xmlResult;
    }

}
