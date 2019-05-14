package com.sxops.www.linfen.dao.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "l_task_config")
public class LTaskConfig {
    /**
     * 任务ID
     */
    @Id
    @Column(name = "task_uuid")
    private String taskUuid;

    /**
     * 任务类型
     */
    @Column(name = "task_type")
    private Integer taskType;

    /**
     * 任务权重
     */
    @Column(name = "task_weight")
    private BigDecimal taskWeight;

    /**
     * 支付金额
     */
    @Column(name = "task_pay_amount")
    private BigDecimal taskPayAmount;

    /**
     * 任务开始时间
     */
    @Column(name = "task_start_time")
    private Date taskStartTime;

    /**
     * 任务结束时间
     */
    @Column(name = "task_end_time")
    private Date taskEndTime;

    /**
     * 任务时长(分钟为单位)
     */
    @Column(name = "tsk_time")
    private Integer tskTime;

    /**
     * 任务状态:0,未激活 1.已激活.2已过期
     */
    @Column(name = "task_status")
    private Integer taskStatus;

    /**
     * 任务评价/建议
     */
    @Column(name = "task_evaluation")
    private String taskEvaluation;

    /**
     * 获取任务ID
     *
     * @return task_uuid - 任务ID
     */
    public String getTaskUuid() {
        return taskUuid;
    }

    /**
     * 设置任务ID
     *
     * @param taskUuid 任务ID
     */
    public void setTaskUuid(String taskUuid) {
        this.taskUuid = taskUuid;
    }

    /**
     * 获取任务类型
     *
     * @return task_type - 任务类型
     */
    public Integer getTaskType() {
        return taskType;
    }

    /**
     * 设置任务类型
     *
     * @param taskType 任务类型
     */
    public void setTaskType(Integer taskType) {
        this.taskType = taskType;
    }

    /**
     * 获取任务权重
     *
     * @return task_weight - 任务权重
     */
    public BigDecimal getTaskWeight() {
        return taskWeight;
    }

    /**
     * 设置任务权重
     *
     * @param taskWeight 任务权重
     */
    public void setTaskWeight(BigDecimal taskWeight) {
        this.taskWeight = taskWeight;
    }

    /**
     * 获取支付金额
     *
     * @return task_pay_amount - 支付金额
     */
    public BigDecimal getTaskPayAmount() {
        return taskPayAmount;
    }

    /**
     * 设置支付金额
     *
     * @param taskPayAmount 支付金额
     */
    public void setTaskPayAmount(BigDecimal taskPayAmount) {
        this.taskPayAmount = taskPayAmount;
    }

    /**
     * 获取任务开始时间
     *
     * @return task_start_time - 任务开始时间
     */
    public Date getTaskStartTime() {
        return taskStartTime;
    }

    /**
     * 设置任务开始时间
     *
     * @param taskStartTime 任务开始时间
     */
    public void setTaskStartTime(Date taskStartTime) {
        this.taskStartTime = taskStartTime;
    }

    /**
     * 获取任务结束时间
     *
     * @return task_end_time - 任务结束时间
     */
    public Date getTaskEndTime() {
        return taskEndTime;
    }

    /**
     * 设置任务结束时间
     *
     * @param taskEndTime 任务结束时间
     */
    public void setTaskEndTime(Date taskEndTime) {
        this.taskEndTime = taskEndTime;
    }

    /**
     * 获取任务时长(分钟为单位)
     *
     * @return tsk_time - 任务时长(分钟为单位)
     */
    public Integer getTskTime() {
        return tskTime;
    }

    /**
     * 设置任务时长(分钟为单位)
     *
     * @param tskTime 任务时长(分钟为单位)
     */
    public void setTskTime(Integer tskTime) {
        this.tskTime = tskTime;
    }

    /**
     * 获取任务状态:0,未激活 1.已激活.2已过期
     *
     * @return task_status - 任务状态:0,未激活 1.已激活.2已过期
     */
    public Integer getTaskStatus() {
        return taskStatus;
    }

    /**
     * 设置任务状态:0,未激活 1.已激活.2已过期
     *
     * @param taskStatus 任务状态:0,未激活 1.已激活.2已过期
     */
    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }

    /**
     * 获取任务评价/建议
     *
     * @return task_evaluation - 任务评价/建议
     */
    public String getTaskEvaluation() {
        return taskEvaluation;
    }

    /**
     * 设置任务评价/建议
     *
     * @param taskEvaluation 任务评价/建议
     */
    public void setTaskEvaluation(String taskEvaluation) {
        this.taskEvaluation = taskEvaluation;
    }
}