package com.sxops.www.task;

import com.sxops.www.base.BaseJunit;
import com.sxops.www.scheduler.task.MyTask;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class EmployeeTaskJunit extends BaseJunit {

    @Autowired
    MyTask employeeTask;

    @Test
    public void taskStart() {
        employeeTask.taskStart();
    }

}
