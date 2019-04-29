package com.camelotchina.www.scheduler.config;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>Description: [配置Quartz的Scheduler对象]</p>
 * Copyright (c) 2017 北京柯莱特科技有限公司
 * Created on 2017年11月10日
 *
 * @author <a href="mailto: yinguijin@camelotchina.com">尹归晋</a>
 * @version 1.0
 */
@Configuration
public class SchedulerConfig {

    /**
     * Discription:[方Quartz获取Scheduler对象单例]
     *
     * Created on 2017/11/10
     * @author: 尹归晋
     */
    @Bean(name="scheduler")
    public Scheduler getScheduler() {
        Scheduler scheduler = null;
        try {
            scheduler = StdSchedulerFactory.getDefaultScheduler();
        } catch (SchedulerException se) {
            se.printStackTrace();
        }
        return scheduler;
    }

}
