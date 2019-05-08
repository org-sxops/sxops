package com.sxops.www.scheduler.listener;

import com.sxops.www.scheduler.task.MyTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * <p>Description: [springboot上下文加载完成监听]</p>
 * Copyright (c) 2017 山西省壹加柒网络技术有限公司
 * Created on 2017年11月10日
 *
 * @author <a href="mailto: yinguijin@sxops.com">尹归晋</a>
 * @version 1.0
 */
@Component
public class StartupListener implements ApplicationListener<ContextRefreshedEvent> {

    /** 任务对象 */
    @Autowired
    MyTask myTask;


    /**
     * Discription:[应用启动事件]
     * @param event springboot上下文加载完成事件
     *
     * Created on 2017/11/10
     * @author: 尹归晋
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        myTask.taskStart();
    }
}
