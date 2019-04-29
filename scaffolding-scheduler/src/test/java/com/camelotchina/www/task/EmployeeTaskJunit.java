package com.camelotchina.www.task;

import com.camelotchina.www.base.BaseJunit;
import com.camelotchina.www.scheduler.task.MyTask;
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
