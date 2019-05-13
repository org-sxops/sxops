package com.sxops.www.dao.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "l_journey_server")
public class LJourneyServer {
    /**
     * ID
     */
    @Id
    private Long id;

    /**
     * 用户UUID
     */
    @Column(name = "user_info_uuid")
    private String userInfoUuid;

    /**
     * 始发地
     */
    @Column(name = "originating_place")
    private String originatingPlace;

    /**
     * 目的地
     */
    @Column(name = "objective_place")
    private String objectivePlace;

    /**
     * 预计出发时间
     */
    @Column(name = "es_departure_time")
    private Date esDepartureTime;

    /**
     * 预计到达时间
     */
    @Column(name = "es_arrival_time")
    private Date esArrivalTime;

    /**
     * 预计行驶时间
     */
    @Column(name = "driving_time")
    private String drivingTime;

    /**
     * 简短描述
     */
    private String describe;

    /**
     * 任务UUID
     */
    @Column(name = "task_uuid")
    private String taskUuid;

    /**
     * 获取ID
     *
     * @return id - ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置ID
     *
     * @param id ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取用户UUID
     *
     * @return user_info_uuid - 用户UUID
     */
    public String getUserInfoUuid() {
        return userInfoUuid;
    }

    /**
     * 设置用户UUID
     *
     * @param userInfoUuid 用户UUID
     */
    public void setUserInfoUuid(String userInfoUuid) {
        this.userInfoUuid = userInfoUuid;
    }

    /**
     * 获取始发地
     *
     * @return originating_place - 始发地
     */
    public String getOriginatingPlace() {
        return originatingPlace;
    }

    /**
     * 设置始发地
     *
     * @param originatingPlace 始发地
     */
    public void setOriginatingPlace(String originatingPlace) {
        this.originatingPlace = originatingPlace;
    }

    /**
     * 获取目的地
     *
     * @return objective_place - 目的地
     */
    public String getObjectivePlace() {
        return objectivePlace;
    }

    /**
     * 设置目的地
     *
     * @param objectivePlace 目的地
     */
    public void setObjectivePlace(String objectivePlace) {
        this.objectivePlace = objectivePlace;
    }

    /**
     * 获取预计出发时间
     *
     * @return es_departure_time - 预计出发时间
     */
    public Date getEsDepartureTime() {
        return esDepartureTime;
    }

    /**
     * 设置预计出发时间
     *
     * @param esDepartureTime 预计出发时间
     */
    public void setEsDepartureTime(Date esDepartureTime) {
        this.esDepartureTime = esDepartureTime;
    }

    /**
     * 获取预计到达时间
     *
     * @return es_arrival_time - 预计到达时间
     */
    public Date getEsArrivalTime() {
        return esArrivalTime;
    }

    /**
     * 设置预计到达时间
     *
     * @param esArrivalTime 预计到达时间
     */
    public void setEsArrivalTime(Date esArrivalTime) {
        this.esArrivalTime = esArrivalTime;
    }

    /**
     * 获取预计行驶时间
     *
     * @return driving_time - 预计行驶时间
     */
    public String getDrivingTime() {
        return drivingTime;
    }

    /**
     * 设置预计行驶时间
     *
     * @param drivingTime 预计行驶时间
     */
    public void setDrivingTime(String drivingTime) {
        this.drivingTime = drivingTime;
    }

    /**
     * 获取简短描述
     *
     * @return describe - 简短描述
     */
    public String getDescribe() {
        return describe;
    }

    /**
     * 设置简短描述
     *
     * @param describe 简短描述
     */
    public void setDescribe(String describe) {
        this.describe = describe;
    }

    /**
     * 获取任务UUID
     *
     * @return task_uuid - 任务UUID
     */
    public String getTaskUuid() {
        return taskUuid;
    }

    /**
     * 设置任务UUID
     *
     * @param taskUuid 任务UUID
     */
    public void setTaskUuid(String taskUuid) {
        this.taskUuid = taskUuid;
    }
}